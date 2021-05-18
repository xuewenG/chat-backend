package me.xuewen.chat.controller.email;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import me.xuewen.chat.constant.code.EmailResponseCode;
import me.xuewen.chat.controller.email.bean.GetCodeReq;
import me.xuewen.chat.entity.Response;
import me.xuewen.chat.service.EmailService;
import me.xuewen.chat.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
@RestController
@RequestMapping("/email")
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private EmailService emailService;

    @GetMapping("canUse")
    public Response canUse(@RequestParam @NotBlank String email) {
        if (StrUtil.isBlank(email) || !Validator.isEmail(email)) {
            return ResultUtil.error(EmailResponseCode.EMAIL_INVALID, "该邮件地址不可用");
        }
        boolean exist = emailService.isExist(email);
        if (exist) {
            return ResultUtil.error(EmailResponseCode.EMAIL_INVALID, "该邮件地址不可用");
        }
        return ResultUtil.success();
    }

    @PostMapping("getVerifyCode")
    public Response getVerifyCode(@RequestBody @Validated GetCodeReq getCodeReq) {
        String email = getCodeReq.getEmail();
        if (!Validator.isEmail(email)) {
            return ResultUtil.error(EmailResponseCode.EMAIL_FORMAT_WRONG, "邮箱格式不正确");
        }
        String verifyCode = RandomUtil.randomNumbers(6);
        LOGGER.debug("邮箱: " + email + "，验证码: " + verifyCode);
        emailService.sendMail(email, "邮箱验证码", "您的邮箱验证码是" + verifyCode + "。");
        redisTemplate.opsForValue().set("verifyCode" + email, verifyCode);
        return ResultUtil.success();
    }
}
