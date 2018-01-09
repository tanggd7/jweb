package net.core.service;

import net.core.dao.UserMapper;
import net.core.exception.BusinessException;
import net.core.po.Sys_organization;
import net.core.po.Sys_resource;
import net.core.po.Sys_role;
import net.core.po.Sys_user;
import net.core.utils.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户信息-业务层
 *
 * @Author 汤国栋
 * @Date 2017-07-11 14:43
 */
@Service
public final class UserService extends BaseService {

    @Resource
    private UserMapper userMapper;

    public Sys_user getSysUserObj(String userName) {
        List<Sys_user> sys_users = userMapper.getSysUserObj(userName);
        if (sys_users.size() > 1) {
            throw new BusinessException("用户账号不唯一");
        } else if (sys_users.size() == 0) {
            return new Sys_user();
        } else {
            return sys_users.get(0);
        }
    }

    public Sys_organization getSysOrganizationObj(String userName) {
        List<Sys_organization> sys_organizations = userMapper.getSysOrganizationObj(userName);
        if (sys_organizations.size() > 1) {
            throw new BusinessException("用户组织不唯一");
        } else if (sys_organizations.size() == 0) {
            return new Sys_organization();
        } else {
            return sys_organizations.get(0);
        }
    }

    public List<Sys_role> getSysRoleObj(String userName) {
        return userMapper.getSysRoleObj(userName);
    }

    public List<Sys_resource> getSysResourceObj(String userName) {
        return userMapper.getSysResourceObj(userName);
    }

    public Set<String> getUserRoles(String userName) {
        Set<String> roles = new HashSet<>();
        for (Sys_role sys_role : getSysRoleObj(userName)) {
            roles.add(sys_role.getRole());
        }
        return roles;
    }

    public Set<String> getUserPermissions(String userName) {
        Set<String> permissions = new HashSet<>();
        for (Sys_resource sys_resource : getSysResourceObj(userName)) {
            if (BaseUtil.isNotEmpty(sys_resource.getPermission())) {
                permissions.add(sys_resource.getPermission());
            }
        }
        return permissions;
    }

}
