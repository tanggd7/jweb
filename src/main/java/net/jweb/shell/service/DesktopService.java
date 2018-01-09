package net.jweb.shell.service;

import net.core.entity.Account;
import net.core.entity.ResourceTree;
import net.core.exception.BusinessException;
import net.core.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主页-业务层
 *
 * @Author 汤国栋
 * @Date 2017-07-27 14:57
 */
@Service
public class DesktopService extends BaseService {

    /**
     * 获取菜单资源
     *
     * @return
     */
    public List<ResourceTree> getSideMenu() {
        Account account = commonService.getAccount();
        if (account == null) {
            throw new BusinessException("获取用户对象异常");
        }
        return commonService.getResourceTree();
    }

}
