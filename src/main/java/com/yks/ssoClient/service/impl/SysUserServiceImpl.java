package com.yks.ssoClient.service.impl;

import com.yks.ssoClient.dao.UserRepository;
import com.yks.ssoClient.dto.Sys_User;
import com.yks.ssoClient.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 17:43
 * @Desc 用户信息业务处理接口实现
 **/
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private UserRepository repository;

    @Override
    public Sys_User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
