package net.jweb.shell.controller;

import net.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误-控制层
 *
 * @Author 汤国栋
 * @Date 2017-07-03 9:33
 */
@Controller
public class ErrorController extends BaseController {

    // 对未找到页面统一显示404页面
    @Override
    @RequestMapping(value = "/*")
    protected String handleRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        return "shell/404Page";
    }

    /**
     * 未授权页面
     *
     * @return
     */
    @RequestMapping(value = "/401Page")
    public String unauthorized() {
        return "shell/401Page";
    }

}
