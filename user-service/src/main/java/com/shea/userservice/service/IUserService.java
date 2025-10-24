package com.shea.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shea.userservice.entity.dto.UserLoginDTO;
import com.shea.userservice.entity.dto.UserRegisterDTO;
import com.shea.userservice.entity.pojo.User;
import com.shea.userservice.entity.vo.UserLoginVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(UserLoginDTO param);

    void register(UserRegisterDTO param);

    String sendEmailCode(String email);
}
