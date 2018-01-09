package net.jweb.shell.controller;

import net.core.controller.BaseController;
import net.core.utils.BaseUtil;
import net.jweb.shell.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录-控制层
 *
 * @Author 汤国栋
 * @Date 2017-06-30 17:22
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @Resource
    private LoginService loginService;

    @Override
    protected String handleRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        model.addAttribute("error", BaseUtil.isEmpty(exceptionClassName) ? "" : "用户名或密码错误");
        return "shell/login";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        loginService.logout();
        return "redirect:../logout";
    }

}
