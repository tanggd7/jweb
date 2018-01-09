package net.jweb.sys.controller;

import net.core.controller.BaseController;
import net.core.entity.PageData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理-控制层
 *
 * @Author 汤国栋
 * @Date 2017-07-24 15:26
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {

    @Override
    @RequiresPermissions("sysUser:page")
    protected String handleRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        return redirectMainPage("sys/sysUser", "sysUser", model);
    }

    @RequiresPermissions("sysUser:page")
    @RequestMapping(value = "/getDateTable")
    @ResponseBody
    public Map<String, Object> getUserList(PageData pageData) {
        Map<String, Object> map = new HashMap<>();
        map.put("draw", pageData.getDraw());
        map.put("recordsTotal", 57);
        map.put("recordsFiltered", 57);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("first_name", "Martena");
            map1.put("last_name", "Mccray");
            map1.put("position", "Post-Sales support");
            map1.put("office", "Edinburgh");
            map1.put("start_date", pageData.getDraw());
            map1.put("salary", pageData.getDraw());
            list.add(map1);
        }
        map.put("data", list);
        return map;
    }

}
