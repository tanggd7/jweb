package net.core.service;

import net.core.entity.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 缓存-业务层
 *
 * @Author 汤国栋
 * @Date 2017-07-26 11:55
 */
@Service
class CacheService {

    @Resource
    private UserService userService;

    @Cacheable(value = "SysUser", key = "#sessionId")
    public Account getAccount(String sessionId, String userName) {
        Account account = new Account();
        account.setUser(userService.getSysUserObj(userName));
        account.setOrganization(userService.getSysOrganizationObj(userName));
        account.setRoles(userService.getSysRoleObj(userName));
        account.setResources(userService.getSysResourceObj(userName));
        return account;
    }

    @CacheEvict(value = "SysUser", key = "#sessionId")
    public void clearAccount(String sessionId) {
        return;
    }

}
