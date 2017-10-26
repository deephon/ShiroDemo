package com.yks.ssoClient.web.controller;

import com.yks.ssoClient.dto.Sys_User;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * @Author 125C01063135
 * @Create 2017-10-24 16:08
 * @Desc 主界面登录处理器
 **/
@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new Sys_User());
        return "login";
    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(value = "user") Sys_User user, HttpServletRequest request, Map<String, Object> map) {
        LOGGER.info("=================登录开始===============");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        LOGGER.info("ShiroException:" + exception);
        StringBuilder msg = new StringBuilder();
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                LOGGER.info("UnknownAccountException ------>  账号不存在");
                msg.append("UnknownAccountException ------>  账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                LOGGER.info("IncorrectCredentialsException ------>  密码不正确");
                msg.append("IncorrectCredentialsException ------>  密码不正确");
            } else if ("kaptchaValidateFailed".equals(exception)) {
                LOGGER.info("kaptchaValidateFailed  ------>  验证码错误");
                msg.append("kaptchaValidateFailed  ------>  验证码错误");
            } else {
                LOGGER.info("elseException ------>" + exception);
                msg.append("elseException ------>" + exception);
            }
        }
        map.put("msg", msg);
        return "login";
    }
}
