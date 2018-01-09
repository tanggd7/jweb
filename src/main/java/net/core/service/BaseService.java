package net.core.service;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * 基类Service，便于今后扩展。 抽象类：主要包含常用参数和方法。
 *
 * @author 汤国栋
 * @date 2016年7月14日 上午9:14:18
 */
public abstract class BaseService {

    @Resource(name = "configProperties")
    protected Properties properties;
    @Resource
    protected CommonService commonService;

}
