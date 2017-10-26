package com.yks.ssoClient.dto;

import javax.persistence.*;
import java.util.List;

/**
 * @Author 125C01063135
 * @Create 2017-10-23 11:07
 * @Desc 权限信息类
 **/
@Entity
public class Sys_Permission {
    @Id
    @GeneratedValue
    private long id;    //标识
    private String name;    //权限名称

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;    //资源类型,[menu|button]
    private String url; //资源路径
    private String permission;  //权限字符串，menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId;  //父编号
    private String parentIds;   //父编号列表
    private Boolean available = Boolean.FALSE;  //是否可用

    //权限--角色关系定义
    @ManyToMany
    @JoinTable(name = "Sys_Role_Permission",joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Sys_Role> roleList;    //一个权限对应多个角色

    public long getId() {
        return id;
    }

    public Sys_Permission setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sys_Permission setName(String name) {
        this.name = name;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Sys_Permission setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Sys_Permission setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Sys_Permission setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Sys_Permission setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getParentIds() {
        return parentIds;
    }

    public Sys_Permission setParentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Sys_Permission setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public List<Sys_Role> getRoleList() {
        return roleList;
    }

    public Sys_Permission setRoleList(List<Sys_Role> roleList) {
        this.roleList = roleList;
        return this;
    }

    @Override
    public String toString() {
        return "Sys_Permission{" + "id=" + id + ", name='" + name + '\'' + ", resourceType='" + resourceType + '\'' + ", url='" + url + '\'' + ", permission='" + permission + '\'' + ", parentId=" + parentId + ", parentIds='" + parentIds + '\'' + ", available=" + available + '}';
    }
}
