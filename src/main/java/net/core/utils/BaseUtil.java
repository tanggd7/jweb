package net.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 基本工具类
 *
 * @author 汤国栋
 * @date 2016年7月6日 上午8:59:28
 */
public final class BaseUtil {

    /**
     * 判断String为空，空true，非空false。
     *
     * @param string 字符串
     * @return true或false
     */
    public static boolean isEmpty(String string) {
        return string == null || string == "" || string.length() <= 0 ? true : false;
    }

    /**
     * 判断Array为空，空true，非空false。
     *
     * @param array 数组
     * @return true或false
     */
    public static boolean isEmpty(Object[] array) {
        return (array != null) && (array.length > 0) ? false : true;
    }

    /**
     * 判断List为空，空true，非空false。
     *
     * @param list List集合
     * @return true或false
     */
    public static boolean isEmpty(List<Object> list) {
        return (null != list) && (list.size() > 0) ? false : true;
    }

    /**
     * 判断Map为空，空true，非空false。
     *
     * @param map Map集合
     * @return true或false
     */
    public static boolean isEmpty(Map<String, Object> map) {
        return (null != map) && (!map.isEmpty()) ? false : true;
    }

    /**
     * 判断String不为空，空false，非空true。
     *
     * @param string 字符串
     * @return true或false
     */
    public static boolean isNotEmpty(String string) {
        return string == null || string == "" || string.length() <= 0 ? false : true;
    }

    /**
     * 判断Array不为空，空false，非空true。
     *
     * @param array 数组
     * @return true或false
     */
    public static boolean isNotEmpty(Object[] array) {
        return (null != array) && (array.length > 0) ? true : false;
    }

    /**
     * 判断List不为空，空false，非空true。
     *
     * @param list List集合
     * @return true或false
     */
    public static boolean isNotEmpty(List<Object> list) {
        return (null != list) && (list.size() > 0) ? true : false;
    }

    /**
     * 判断Map不为空，空false，非空true。
     *
     * @param map Map集合
     * @return true或false
     */
    public static boolean isNotEmpty(Map<String, Object> map) {
        return (null != map) && (!map.isEmpty()) ? true : false;
    }

    /**
     * 格式化Map中的数据为字符串类型。
     *
     * @param map Map集合
     * @param key 键
     * @return 值
     */
    public static String getMapValue(Map<String, Object> map, String key) {
        return (map.containsKey(key)) && (map.get(key) != null) ? map.get(key).toString().trim() : "";
    }

    /**
     * 格式化Map中的数据为字符串类型，defaultValue：替换值。
     *
     * @param map          Map集合
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getMapValue(Map<String, Object> map, String key, String defaultValue) {
        return (map.containsKey(key)) && (map.get(key) != null) ? map.get(key).toString().trim() : defaultValue;
    }

    /**
     * 将map转换成对应的entity实体类（实体类中必须拥有类变量的set方法）。
     *
     * @param map Map集合
     * @param obj Entity实体
     * @return Entity实体
     */
    public static Object mapToEntity(Map<String, Object> map, Object obj) {
        try {
            for (Entry<String, Object> entry : map.entrySet()) {
                Class c = obj.getClass();
                Field field = c.getDeclaredField(entry.getKey());
                Method method = c.getMethod("set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1), new Class[]{field.getType()});
                method.invoke(obj, entry.getValue());
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("转换实体类出错", e);
        }
    }

    /**
     * 格式化List
     *
     * @param list List集合
     * @return {rows=[{},{}],total=0}
     */
    public static Map<String, Object> formatData(List<Map<String, Object>> list) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            map.put("row_num", i + 1);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", list);
        result.put("total", "" + (list.size() + 1));
        return result;
    }

}
