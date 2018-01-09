package net.core.realm;

import net.core.po.Sys_user;
import net.core.service.UserService;
import net.core.utils.BaseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;


/**
 * 用户验证
 *
 * @Author 汤国栋
 * @Date 2017-06-20 16:48
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 权限授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.getUserRoles(userName));
        simpleAuthorizationInfo.setStringPermissions(userService.getUserPermissions(userName));
        return simpleAuthorizationInfo;
    }

    /**
     * 身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        Sys_user sysUser = userService.getSysUserObj(userName);
        if (BaseUtil.isEmpty(sysUser.getUsername())) {
            return new SimpleAuthenticationInfo(userName, "", null, getName());
        }
        String salt = String.valueOf(sysUser.getUsername()) + String.valueOf(sysUser.getSalt());
        return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPassword(), ByteSource.Util.bytes(salt), getName());
    }

}
