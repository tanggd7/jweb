package net.core.po;

import java.io.Serializable;

public class Sys_organization_role implements Serializable {

    private Long organization_id;
    private Long role_id;

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
