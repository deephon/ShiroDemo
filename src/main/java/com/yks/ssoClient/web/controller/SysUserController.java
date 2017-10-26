package com.yks.ssoClient.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 125C01063135
 * @Create 2017-10-25 10:11
 * @Desc 用户信息处理控制器
 **/
@Controller
@RequestMapping("/userInfo")
public class SysUserController {

    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/userList")
    public String userInfo() {
        return "userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userAdd() {
        return "userInfoAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel() {
        return "userInfoDel";
    }
}
