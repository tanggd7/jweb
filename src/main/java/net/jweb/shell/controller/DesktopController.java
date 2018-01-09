package net.jweb.shell.controller;

import net.core.controller.BaseController;
import net.core.entity.ResourceTree;
import net.jweb.shell.service.DesktopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 主页-控制层
 *
 * @Author 汤国栋
 * @Date 2017-07-03 9:28
 */
@Controller
@RequestMapping(value = "/desktop")
public class DesktopController extends BaseController {

    @Resource
    private DesktopService desktopService;

    @Override
    protected String handleRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        return "shell/desktop";
    }

    @RequestMapping(value = "/getSideMenu")
    @ResponseBody
    public List<ResourceTree> getSideMenu() {
        return desktopService.getSideMenu();
    }

}
