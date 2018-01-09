package net.core.entity;

import net.core.po.Sys_resource;

import java.io.Serializable;
import java.util.List;

/**
 * 资源实体类
 *
 * @Author 汤国栋
 * @Date 2017-07-28 9:59
 */
public class ResourceTree implements Serializable {

    private Long id;
    private String name;
    private String type;
    private String url;
    private String description;
    private Long parent_id;
    private String parent_ids;
    private String permission;
    private String icon;
    private List<ResourceTree> resourceTrees;

    public ResourceTree() {

    }

    public ResourceTree(Sys_resource sys_resource) {
        this.id = sys_resource.getId();
        this.name = sys_resource.getName();
        this.type = sys_resource.getType();
        this.url = sys_resource.getUrl();
        this.description = sys_resource.getDescription();
        this.parent_id = sys_resource.getParent_id();
        this.parent_ids = sys_resource.getParent_ids();
        this.permission = sys_resource.getPermission();
        this.icon = sys_resource.getIcon();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ResourceTree> getResourceTrees() {
        return resourceTrees;
    }

    public void setResourceTrees(List<ResourceTree> resourceTrees) {
        this.resourceTrees = resourceTrees;
    }

}
