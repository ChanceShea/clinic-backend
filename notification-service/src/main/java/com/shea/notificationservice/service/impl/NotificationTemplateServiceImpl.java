package com.shea.notificationservice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shea.exception.ClientException;
import com.shea.notificationservice.entity.dto.NotificationTemplateAddDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplatePageDTO;
import com.shea.notificationservice.entity.dto.NotificationTemplateUpdateDTO;
import com.shea.notificationservice.entity.pojo.NotificationTemplate;
import com.shea.notificationservice.entity.vo.NotificationTemplateVO;
import com.shea.notificationservice.mapper.NotificationTemplateMapper;
import com.shea.notificationservice.service.INotificationTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息模板表 服务实现类
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@Service
public class NotificationTemplateServiceImpl extends ServiceImpl<NotificationTemplateMapper, NotificationTemplate> implements INotificationTemplateService {

    /**
     * 根据查询条件分页获取通知模板列表
     * @param param 查询参数对象，包含模板名称、模板类型、状态、创建时间范围、更新时间范围、页码、页面大小等条件
     * @return 分页结果对象，包含通知模板VO列表及分页信息
     */
    @Override
    public Page<NotificationTemplateVO> queryTemplateByPage(NotificationTemplatePageDTO param) {
        // 构建查询条件包装器
        LambdaQueryWrapper<NotificationTemplate> qw = Wrappers.lambdaQuery(NotificationTemplate.class)
                .like(StrUtil.isNotBlank(param.getTemplateName()), NotificationTemplate::getTemplateName, param.getTemplateName())
                .eq(ObjectUtil.isNotNull(param.getTemplateType()), NotificationTemplate::getTemplateType, param.getTemplateType())
                .eq(ObjectUtil.isNotNull(param.getStatus()), NotificationTemplate::getStatus, param.getStatus())
                .ge(ObjectUtil.isNotNull(param.getStartCreateTime()), NotificationTemplate::getCreatedTime, param.getStartCreateTime())
                .le(ObjectUtil.isNotNull(param.getEndCreateTime()), NotificationTemplate::getCreatedTime, param.getEndCreateTime())
                .ge(ObjectUtil.isNotNull(param.getStartUpdateTime()), NotificationTemplate::getUpdatedTime, param.getStartUpdateTime())
                .le(ObjectUtil.isNotNull(param.getEndUpdateTime()), NotificationTemplate::getUpdatedTime, param.getEndUpdateTime());

        // 执行分页查询
        Page<NotificationTemplate> pageResult = baseMapper.selectPage(new Page<>(param.getPageNum(), param.getPageSize()), qw);

        // 转换查询结果为VO对象列表
        List<NotificationTemplate> records = pageResult.getRecords();
        List<NotificationTemplateVO> collect = records.stream().map(item -> {
            NotificationTemplateVO vo = new NotificationTemplateVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();

        // 构建返回的分页结果对象
        Page<NotificationTemplateVO> page = new Page<>();
        page.setTotal(pageResult.getTotal());
        page.setRecords(collect);
        page.setCurrent(pageResult.getCurrent());
        page.setSize(pageResult.getSize());
        return page;

    }

    /**
     * 添加通知模板
     * @param param 通知模板添加参数对象，包含模板代码、模板名称、模板内容等信息
     */
    @Override
    public void addNotificationTemplate(NotificationTemplateAddDTO param) {
        String templateCode = param.getTemplateCode();
        // 构造查询条件，根据模板代码查询是否已存在相同模板
        LambdaQueryWrapper<NotificationTemplate> eq = Wrappers.lambdaQuery(NotificationTemplate.class)
                .eq(NotificationTemplate::getTemplateCode, templateCode);

        // 检查模板代码是否已存在，如果存在则抛出异常
        if (ObjectUtil.isNotNull(baseMapper.selectOne(eq))){
            throw new ClientException(String.format("模板代码[%s]已存在", templateCode));
        }
        NotificationTemplate one = new NotificationTemplate();
        // 将参数对象属性复制到通知模板实体对象
        BeanUtils.copyProperties(param, one);
        baseMapper.insert(one);
    }

    /**
     * 删除通知模板
     * @param id 通知模板ID
     */
    @Override
    public void removeNotificationTemplate(Long id) {
        // 构造删除条件，根据ID删除对应的通知模板
        LambdaQueryWrapper<NotificationTemplate> eq = Wrappers.lambdaQuery(NotificationTemplate.class)
                .eq(NotificationTemplate::getId, id);
        baseMapper.delete(eq);
    }

    /**
     * 更新通知模板
     * @param param 通知模板更新参数对象，包含模板ID、模板代码、模板名称、模板内容等信息
     */
    @Override
    public void updateNotificationTemplate(NotificationTemplateUpdateDTO param) {
        // 构造查询条件，根据ID查询要更新的通知模板
        LambdaUpdateWrapper<NotificationTemplate> eq = Wrappers.lambdaUpdate(NotificationTemplate.class)
                .eq(NotificationTemplate::getId, param.getId());
        NotificationTemplate one = baseMapper.selectOne(eq);
        // 检查更新后的模板代码是否与原模板代码相同，如果不同则抛出异常
        if (one.getTemplateCode().equals(param.getTemplateCode())){
            throw new ClientException(String.format("模板代码[%s]已存在", param.getTemplateCode()));
        }
        // 将参数对象属性复制到通知模板实体对象
        BeanUtils.copyProperties(param, one);
        baseMapper.updateById(one);
    }

}
