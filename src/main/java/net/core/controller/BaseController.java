package net.core.controller;

import net.core.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 基类Controller，便于今后扩展。 抽象类：主要包含常用参数和方法。
 *
 * @author 汤国栋
 * @date 2016年7月14日 上午9:11:34
 */
public abstract class BaseController {

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource(name = "configProperties")
    protected Properties properties;
    @Resource
    private CommonService commonService;

    @RequestMapping
    protected abstract String handleRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception;

    /**
     * 对查询列表类型的页面统一由一个html处理，只需要提供所用到的js地址，查询条件、列表字段配置文件json地址。
     *
     * @param resfile 页面加载资源路径（原页面需要加载的查询条件和列表字段查询条件的配置文件json，javascript）
     * @param url     返回的路径
     * @param model   模板对象
     * @return
     */
    protected String redirectMainPage(String resfile, String url, Model model) {
        model.addAttribute("resfile", resfile);
        model.addAttribute("url", url);
        return "shell/main";
    }

    /**
     * 获取request中的参数，多个参数以","隔开。
     *
     * @param request
     * @return
     * @author 汤国栋
     * @date 2016年12月23日 下午1:55:37
     */
    protected Map<String, Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String[] value = entry.getValue();
            result.put(entry.getKey(), value == null || value.length == 0 ? "" : StringUtils.join(value, ","));
        }
        return result;
    }

}
