package com.yks.ssoClient.service;

import com.yks.ssoClient.dto.Sys_User;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 17:42
 * @Desc 用户信息业务处理接口
 **/
public interface ISysUserService {

    /**
     * 根据账号获取用户信息
     * @param username
     * @return
     */
    public Sys_User findByUsername(String username);
}
