package com.yks.ssoClient.dto;

import javax.persistence.*;
import java.util.List;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 10:54
 * @Desc 角色信息类
 **/
@Entity
public class Sys_Role {
    @Id
    @GeneratedValue
    private long id;    //编号
    @Column(unique = true)
    private String roleName;    //角色名称
    private String description; //角色描述，UI界面显示使用
    private Boolean available = Boolean.FALSE;  // 是否可用,如果不可用将不会添加给用户
    // 角色--用户关系定义
    @ManyToMany
    @JoinTable(name = "Sys_User_Role",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<Sys_User> userList;    //一个角色对应多个用户
    // 角色 -- 权限关系定义
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Sys_Role_Permission",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Sys_Permission> permissionList;    //一个角色拥有多个权限

    @Override
    public String toString() {
        return "Sys_Role{" + "id=" + id + ", roleName='" + roleName + '\'' + ", description='" + description + '\'' + ", available=" + available + '}';
    }

    public List<Sys_Permission> getPermissionList() {
        return permissionList;
    }

    public Sys_Role setPermissionList(List<Sys_Permission> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    public long getId() {
        return id;
    }

    public Sys_Role setId(long id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Sys_Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Sys_Role setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Sys_Role setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<Sys_User> getUserList() {
        return userList;
    }

    public Sys_Role setUserList(List<Sys_User> userList) {
        this.userList = userList;
        return this;
    }
}
