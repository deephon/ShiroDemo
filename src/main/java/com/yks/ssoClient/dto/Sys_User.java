package com.yks.ssoClient.dto;

import javax.persistence.*;
import java.util.List;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 10:48
 * @Desc 用户实体
 **/
@Entity
public class Sys_User {
    @Id
    @GeneratedValue
    private long id;    //用户ID

    @Column(unique = true)
    private String username; //账号
    private String name;    //姓名（昵称或者真实姓名，不同系统不同定义）
    private String password;    //密码
    private String salt;    //密码盐
    private byte state; //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    //用户--角色关系定义
    @ManyToMany(fetch = FetchType.EAGER)    //立即从数据库中进行加载数据
    @JoinTable(name = "Sys_User_Role", joinColumns = {@JoinColumn(name = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Sys_Role> roleList;    //一个用户有多个角色

    @Override
    public String toString() {
        return "Sys_User{" + "id=" + id + ", account='" + username + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", state=" + state + '}';
    }

    public long getId() {
        return id;
    }

    public Sys_User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Sys_User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sys_User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Sys_User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Sys_User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public byte getState() {
        return state;
    }

    public Sys_User setState(byte state) {
        this.state = state;
        return this;
    }

    public List<Sys_Role> getRoleList() {
        return roleList;
    }

    public Sys_User setRoleList(List<Sys_Role> roleList) {
        this.roleList = roleList;
        return this;
    }
}
