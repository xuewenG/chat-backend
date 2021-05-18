package me.xuewen.chat.controller.user;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import me.xuewen.chat.constant.code.UserResponseCode;
import me.xuewen.chat.constant.user.GenderTypeConstant;
import me.xuewen.chat.controller.user.bean.*;
import me.xuewen.chat.entity.Response;
import me.xuewen.chat.entity.User;
import me.xuewen.chat.service.UserService;
import me.xuewen.chat.util.PasswordUtil;
import me.xuewen.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @PostMapping("login")
    public Response login(@RequestBody @Validated LoginReqBean loginReqBean) {
        String credential = loginReqBean.getCredential();
        String password = loginReqBean.getPassword();
        Integer type = loginReqBean.getType();

        User user = userService.login(credential, password, type);
        user.setPassword(null);

        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, user.getId());

        LoginRespBean loginRespBean = new LoginRespBean();
        loginRespBean.setUser(user);
        loginRespBean.setToken(token);
        return ResultUtil.success(loginRespBean);
    }

    @PostMapping("register")
    public Response enroll(@RequestBody @Validated RegisterReqBean registerReqBean) {
        final String DEFAULT_AVATAR = "https://blog-xuewen-me.oss-cn-shanghai.aliyuncs.com/xuewen/chat/avatar.jpeg";
        String email = registerReqBean.getEmail();
        if (!Validator.isEmail(email)) {
            return ResultUtil.error(UserResponseCode.EMAIL_FORMAT_WRONG, "邮箱格式不正确");
        }
        String verifyCode = (String) redisTemplate.opsForValue().get("verifyCode" + email);
        if (verifyCode == null || !verifyCode.equals(registerReqBean.getVerifyCode())) {
            return ResultUtil.error(UserResponseCode.INVALID_VERIFY_CODE, "验证码不正确");
        }
        redisTemplate.delete("verifyCode" + email);
        User emailUser = userService.getByEmail(email);
        if (emailUser != null) {
            return ResultUtil.error(UserResponseCode.EMAIL_REPETITION, "该邮箱已经被使用");
        }
        String account;
        while (true) {
            account = RandomUtil.randomNumbers(10);
            User user = userService.getByAccount(account);
            if (user == null) {
                break;
            }
        }
        String password = PasswordUtil.encode(registerReqBean.getPassword());
        String nickname = registerReqBean.getNickname();
        Integer gender = registerReqBean.getGender();
        if (gender == null) {
            gender = GenderTypeConstant.NOT_SET;
        }
        Timestamp birthday = registerReqBean.getBirthday();
        String avatar = registerReqBean.getAvatar();
        if (avatar == null) {
            avatar = DEFAULT_AVATAR;
        }
        userService.register(account, password, email, nickname, avatar, birthday, gender);
        RegisterRespBean enrollRespBean = new RegisterRespBean();
        enrollRespBean.setAccount(account);
        return ResultUtil.success(enrollRespBean);
    }

    @GetMapping("avatar")
    public Response getAvatarByCredential(@RequestParam String credential) {
        if (StrUtil.isBlank(credential)) {
            return ResultUtil.success(new GetAvatarReqBean(""));
        }
        User accountUser = userService.getByAccount(credential);
        User emailUser = userService.getByEmail(credential);
        User user = null;
        if (accountUser != null) {
            user = accountUser;
        }
        if (emailUser != null) {
            user = emailUser;
        }
        if (user == null) {
            return ResultUtil.success(new GetAvatarReqBean(""));
        }
        return ResultUtil.success(new GetAvatarReqBean(user.getAvatar()));
    }
}
