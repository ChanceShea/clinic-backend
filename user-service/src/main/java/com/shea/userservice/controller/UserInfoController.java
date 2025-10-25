package com.shea.userservice.controller;


import com.shea.userservice.annotation.EncryptField;
import com.shea.Result;
import com.shea.userservice.entity.dto.UserInfoAddDTO;
import com.shea.userservice.entity.vo.UserInfoVO;
import com.shea.userservice.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Shea
 * @since 2025-10-18
 */
@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final IUserInfoService userInfoService;
    @GetMapping("/getUserInfo")
    @EncryptField
    public Result<UserInfoVO> getUserInfo(String userId){
        return Result.success(userInfoService.getUserInfo(userId));
    }

    @PostMapping
    public Result addUserInfo(@RequestBody UserInfoAddDTO param){
        userInfoService.addUserInfo(param);
        return Result.success();
    }

}
