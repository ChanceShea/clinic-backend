package com.shea.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shea.userservice.entity.dto.UserInfoAddDTO;
import com.shea.userservice.entity.dto.UserLoginDTO;
import com.shea.userservice.entity.dto.UserRegisterDTO;
import com.shea.userservice.entity.pojo.User;
import com.shea.userservice.entity.pojo.UserInfo;
import com.shea.userservice.entity.vo.UserInfoVO;
import com.shea.userservice.entity.vo.UserLoginVO;
import com.shea.userservice.exception.ClientException;
import com.shea.userservice.mapper.UserInfoMapper;
import com.shea.userservice.mapper.UserMapper;
import com.shea.userservice.service.IUserInfoService;
import com.shea.userservice.service.IUserService;
import com.shea.userservice.utils.EmailUtil;
import com.shea.userservice.utils.JwtUtil;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.shea.userservice.constant.RedisConstants.EMAIL_CODE_KEY;

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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final EmailUtil emailUtil;
    private final StringRedisTemplate redisTemplate;
    private final JavaMailSender javaMailSender;
    private final IUserInfoService userInfoService;

    @Override
    public UserLoginVO login(UserLoginDTO param) {
        LambdaQueryWrapper<User> eq = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, param.getUsername())
                .eq(User::getPassword, param.getPassword());
        User one = userMapper.selectOne(eq);
        Optional.ofNullable(one).orElseThrow(() -> new RuntimeException("用户名或密码错误"));
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.lambdaQuery(UserInfo.class)
                .eq(UserInfo::getUserId, one.getId());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", one.getUsername());
        claims.put("auth", one.getUserType().toString());
        String token = JwtUtil.createJwt(claims);
        return new UserLoginVO(token,userInfoVO);
    }

    @Override
    public void register(UserRegisterDTO param) {
        LambdaQueryWrapper<User> eq = Wrappers.lambdaQuery(User.class)
                .eq(User::getEmail, param.getEmail());
        if (userMapper.selectOne(eq) != null){
            throw new ClientException("邮箱已注册");
        }
        UserInfoAddDTO dto = new UserInfoAddDTO();
        BeanUtils.copyProperties(param,dto);
        userInfoService.addUserInfo(dto);
    }

    @Override
    public String sendEmailCode(String email){
        if (email == null){
            throw new ClientException("邮箱不能为空");
        }
        String code = redisTemplate.opsForValue().get(EMAIL_CODE_KEY + email);
        if (code == null){
            code = emailUtil.generateEmailCode(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("用户注册邮箱验证码");
            message.setText("尊敬的用户您好!感谢您的注册。\n\n尊敬的用户: 您的校验验证码为: " + code + ",有效期5分钟，请不要把验证码信息泄露给其他人,如非本人请勿操作");
            message.setTo(email);
            try{
                message.setFrom(new InternetAddress("2788456221@qq.com", "**ZWQ-INT", "UTF-8").toString());
                javaMailSender.send(message);
                redisTemplate.opsForValue().set(EMAIL_CODE_KEY + email,code,5L, TimeUnit.MINUTES);
                log.info("发送验证码成功");
                return "发送验证码成功";
            }catch (Exception e){
                log.error("邮件发送出现异常");
                log.error("异常信息为{}", e.getMessage());
                log.error("异常堆栈信息为-->");
                throw new RuntimeException("邮件发送出现异常");
            }
        } else {
            return "验证码已发送";
        }
    }
}
