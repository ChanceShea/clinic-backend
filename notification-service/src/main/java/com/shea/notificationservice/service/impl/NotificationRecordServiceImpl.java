package com.shea.notificationservice.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shea.notificationservice.entity.dto.NotificationRecordPageDTO;
import com.shea.notificationservice.entity.pojo.NotificationRecord;
import com.shea.notificationservice.entity.vo.NotificationRecordVO;
import com.shea.notificationservice.mapper.NotificationRecordMapper;
import com.shea.notificationservice.service.INotificationRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 通知记录表 服务实现类
 * </p>
 *
 * @author Shea
 * @since 2025-10-24
 */
@Service
public class NotificationRecordServiceImpl extends ServiceImpl<NotificationRecordMapper, NotificationRecord> implements INotificationRecordService {

    @Override
    public Page<NotificationRecordVO> queryRecordByPage(NotificationRecordPageDTO param) {
        // 构建查询条件
        LambdaQueryWrapper<NotificationRecord> queryWrapper = Wrappers.lambdaQuery(NotificationRecord.class)
                .eq(StrUtil.isNotBlank(param.getReceiver()), NotificationRecord::getReceiver, param.getReceiver())
                .eq(StrUtil.isNotBlank(param.getSender()), NotificationRecord::getSender, param.getSender())
                .eq(StrUtil.isNotBlank(param.getNotificationType()), NotificationRecord::getNotificationType, param.getNotificationType())
                .eq(StrUtil.isNotBlank(param.getStatus()), NotificationRecord::getStatus, param.getStatus())
                .eq(StrUtil.isNotBlank(param.getBusinessType()), NotificationRecord::getBusinessType, param.getBusinessType())
                .eq(StrUtil.isNotBlank(param.getBusinessId()), NotificationRecord::getBusinessId, param.getBusinessId())
                .ge(ObjectUtil.isNotNull(param.getStartDate()), NotificationRecord::getSentTime, param.getStartDate())
                .le(ObjectUtil.isNotNull(param.getEndDate()), NotificationRecord::getSentTime, param.getEndDate())
                .orderByDesc(NotificationRecord::getSentTime);
        // 将查询出来的数据转换成VO
        Page<NotificationRecord> result = baseMapper.selectPage(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper);
        List<NotificationRecordVO> collect = result.getRecords().stream().map(item -> {
            NotificationRecordVO vo = new NotificationRecordVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        Page<NotificationRecordVO> page = new Page<>();
        page.setRecords(collect);
        page.setTotal(result.getTotal());
        page.setCurrent(result.getCurrent());
        page.setSize(result.getSize());
        return page;
    }
}
