package com.yks.ssoClient.config.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 125C01063135
 * @Create 2017-10-25 14:50
 * @Desc 自定义异常处理器
 **/
public class MyExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionResolver.class);

    /**
     * 定义的filter必须满足filter instanceof AuthorizationFilter，只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，
     * 而anon，authcBasic，auchc，user是AuthenticationFilter，所以unauthorizedUrl设置后页面不跳转
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        LOGGER.info("===============自定义处理异常开始===============");
        if (e instanceof UnauthorizedException) {
            ModelAndView mv = new ModelAndView("/403");
            return mv;
        }
        e.printStackTrace();
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e.toString().replaceAll("\n", "<br/>"));
        return mv;
    }
}
