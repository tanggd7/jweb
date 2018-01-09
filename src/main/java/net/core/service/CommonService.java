package net.core.service;

import net.core.entity.Account;
import net.core.entity.ResourceTree;
import net.core.po.Sys_resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统公用-业务层
 *
 * @Author 汤国栋
 * @Date 2017-07-26 11:39
 */
@Service
public final class CommonService extends BaseService {

    @Resource
    private CacheService cacheService;

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户的用户名
     *
     * @return
     */
    public String getUserName() {
        return String.valueOf(getSubject().getPrincipal());
    }

    /**
     * 获取当前用户的对象
     *
     * @return
     */
    public Account getAccount() {
        return cacheService.getAccount(String.valueOf(getSubject().getSession().getId()), getUserName());
    }

    /**
     * 清除当前用户的对象
     */
    public void clearAccount() {
        cacheService.clearAccount(String.valueOf(getSubject().getSession().getId()));
    }

    /**
     * 获取资源菜单树
     *
     * @return
     */
    public List<ResourceTree> getResourceTree() {
        List<Sys_resource> sys_resources = getAccount().getResources();
        List<Sys_resource> rootList = new ArrayList<>();
        for (Sys_resource sys_resource : sys_resources) {
            if (sys_resource.getType().equals("page")) {
                boolean flag = true;
                for (Sys_resource sysResource : sys_resources) {
                    if (sys_resource.getParent_id().equals(sysResource.getId())) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    rootList.add(sys_resource);
                }
            }
        }

        List<ResourceTree> resourceTrees = new ArrayList<>();
        for (Sys_resource sys_resource : rootList) {
            ResourceTree resourceTree = new ResourceTree(sys_resource);
            generateResourceTree(resourceTree, sys_resources);
            resourceTrees.add(resourceTree);
        }
        return resourceTrees;
    }

    /**
     * 生成资源菜单树
     *
     * @param resourceTree
     * @param sys_resources
     */
    private void generateResourceTree(ResourceTree resourceTree, List<Sys_resource> sys_resources) {
        List<ResourceTree> resourceTrees = new ArrayList<>();
        for (Sys_resource sys_resource : sys_resources) {
            if (sys_resource.getType().endsWith("page") && resourceTree.getId().equals(sys_resource.getParent_id())) {
                resourceTrees.add(new ResourceTree(sys_resource));
            }
        }
        for (ResourceTree tree : resourceTrees) {
            generateResourceTree(tree, sys_resources);
        }
        resourceTree.setResourceTrees(resourceTrees);
    }

}
