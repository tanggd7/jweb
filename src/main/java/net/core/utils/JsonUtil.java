package net.core.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Json格式工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2016年12月13日 上午9:24:23
 */
public final class JsonUtil {

    /**
     * Object转Json字符串
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     * @author 汤国栋
     * @date 2016年12月13日 上午9:25:07
     */
    public static String ObjectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object).toString().trim();
    }

    /**
     * Json字符串转Object
     *
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @author 汤国栋
     * @date 2016年12月13日 上午9:25:30
     */
    public static Object JsonToObject(String json) throws IOException {
        return json.trim().startsWith("[") ? new ObjectMapper().readValue(json, ArrayList.class) : new ObjectMapper().readValue(json, HashMap.class);
    }

}
