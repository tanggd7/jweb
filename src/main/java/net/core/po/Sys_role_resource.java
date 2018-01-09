package net.core.po;

import java.io.Serializable;

public class Sys_role_resource implements Serializable {

    private Long role_id;
    private Long resource_id;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }
}
