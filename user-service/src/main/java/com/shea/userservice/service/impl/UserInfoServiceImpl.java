package com.shea.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shea.userservice.entity.dto.UserInfoAddDTO;
import com.shea.userservice.entity.pojo.User;
import com.shea.userservice.entity.pojo.UserInfo;
import com.shea.userservice.entity.vo.UserInfoVO;
import com.shea.userservice.mapper.UserInfoMapper;
import com.shea.userservice.mapper.UserMapper;
import com.shea.userservice.service.IUserInfoService;
import com.shea.userservice.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfoVO getUserInfo(String userId) {
        UserInfo userInfo = this.getById(userId);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);
        String address = userInfo.getProvince() + userInfo.getCity() + userInfo.getDistrict();
        userInfoVO.setAddress(address);
        return userInfoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserInfo(UserInfoAddDTO param) {
        User user = new User();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(param,user);
        BeanUtils.copyProperties(param,userInfo);
        userInfo.setIsCompleted(0);
        String username = RandomUtil.number(10);
        String uuid = RandomUtil.simpleUUID();
        user.setId(uuid);
        userInfo.setUserId(uuid);
        user.setUsername(username);
        boolean isCompleted = true; // 判断信息是否完善
        for (Object o : userInfo) { // 遍历对象属性
            if (o == null){
                isCompleted = false;
                break;
            }
        }
        if(isCompleted){ // 信息完善
            userInfo.setIsCompleted(1);
        }
        userInfoMapper.insert(userInfo);
        userMapper.insert(user);
    }
}
