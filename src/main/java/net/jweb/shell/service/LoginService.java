package net.jweb.shell.service;

import net.core.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 登录-业务层
 *
 * @Author 汤国栋
 * @Date 2017-08-01 15:56
 */
@Service
public class LoginService extends BaseService {

    /**
     * 退出
     */
    public void logout() {
        commonService.clearAccount();
    }

}
