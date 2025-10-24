package com.shea.userservice.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shea.userservice.entity.dto.UserInfoAddDTO;
import com.shea.userservice.entity.pojo.UserInfo;
import com.shea.userservice.entity.vo.UserInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
public interface IUserInfoService extends IService<UserInfo> {

    UserInfoVO getUserInfo(String userId);

    void addUserInfo(UserInfoAddDTO param);
}
