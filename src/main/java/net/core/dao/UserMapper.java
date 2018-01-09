package net.core.dao;

import net.core.po.Sys_organization;
import net.core.po.Sys_resource;
import net.core.po.Sys_role;
import net.core.po.Sys_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息-持久层
 *
 * @Author 汤国栋
 * @Date 2017-07-11 14:49
 */
public interface UserMapper {

    List<Sys_user> getSysUserObj(@Param(value = "userName") String userName);

    List<Sys_organization> getSysOrganizationObj(@Param(value = "userName") String userName);

    List<Sys_role> getSysRoleObj(@Param(value = "userName") String userName);

    List<Sys_resource> getSysResourceObj(@Param(value = "userName") String userName);

}
