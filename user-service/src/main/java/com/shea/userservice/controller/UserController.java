package com.shea.userservice.controller;


import com.shea.userservice.entity.Result;
import com.shea.userservice.entity.dto.UserLoginDTO;
import com.shea.userservice.entity.dto.UserRegisterDTO;
import com.shea.userservice.entity.vo.UserLoginVO;
import com.shea.userservice.service.IUserService;
import com.shea.userservice.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final EmailUtil emailUtil;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO param){
        return Result.success(userService.login(param));
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO param){
        int res = emailUtil.verifyEmailCode(param.getEmail(), param.getCode());
        if (res == 0){
            return Result.error("验证码错误");
        } else if (res == 2){
            return Result.error("验证码过期");
        }
        userService.register(param);
        return Result.success();
    }

    @PostMapping("/sendEmailCode")
    public Result sendEmailCode(@RequestBody UserRegisterDTO param){
        return Result.success(userService.sendEmailCode(param.getEmail()));
    }
}
