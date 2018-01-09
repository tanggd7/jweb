package net.core.entity;

import net.core.po.Sys_organization;
import net.core.po.Sys_resource;
import net.core.po.Sys_role;
import net.core.po.Sys_user;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体
 *
 * @Author 汤国栋
 * @Date 2017-07-25 11:00
 */
public class Account implements Serializable {

    private Sys_user user;
    private Sys_organization organization;
    private List<Sys_role> roles;
    private List<Sys_resource> resources;

    public Sys_user getUser() {
        return user;
    }

    public void setUser(Sys_user user) {
        this.user = user;
    }

    public Sys_organization getOrganization() {
        return organization;
    }

    public void setOrganization(Sys_organization organization) {
        this.organization = organization;
    }

    public List<Sys_role> getRoles() {
        return roles;
    }

    public void setRoles(List<Sys_role> roles) {
        this.roles = roles;
    }

    public List<Sys_resource> getResources() {
        return resources;
    }

    public void setResources(List<Sys_resource> resources) {
        this.resources = resources;
    }

}
