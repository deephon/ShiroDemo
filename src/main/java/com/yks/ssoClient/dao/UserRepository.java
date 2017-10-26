package com.yks.ssoClient.dao;

import com.yks.ssoClient.dto.Sys_User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 12:10
 * @Desc User持久化类
 **/
public interface UserRepository extends CrudRepository<Sys_User,Long> {
    /**
     * 通过账号查找用户信息
     * @param username
     * @return
     */
    public Sys_User findByUsername(String username);
}
