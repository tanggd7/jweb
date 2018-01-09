package net.core.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * XML工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2017年1月23日 下午5:26:01
 */
public final class XmlUtil {

    /**
     * 解析XML
     *
     * @param xmlStr
     * @return
     * @throws Exception
     * @author 汤国栋
     * @date 2017年1月24日 上午8:55:06
     */
    public static Map<String, Object> parseXml(String xmlStr) {
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            Element elementRoot = document.getRootElement();
            return elementToMap(elementRoot);
        } catch (DocumentException e) {
            throw new RuntimeException("解析XML出错");
        }
    }

    /**
     * 解析遍历XML节点
     *
     * @param element
     * @return
     * @author 汤国栋
     * @date 2017年1月24日 上午8:55:25
     */
    private static Map<String, Object> elementToMap(Element element) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Iterator<Element> iterator = element.elementIterator();
            while (iterator.hasNext()) {
                Element ele = iterator.next();
                for (Entry<String, Object> entry : elementToMap(ele).entrySet()) {
                    paramMap.put(entry.getKey(), entry.getValue());
                }
            }
            // if (element.getTextTrim().equals("")) {
            if (!paramMap.isEmpty() && paramMap != null) {
                result.put(element.getName(), paramMap);
            } else {
                result.put(element.getName(), element.getText());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("解析遍历XML节点出错");
        }
    }

    /**
     * 生成XML数据
     *
     * @param map
     * @return
     * @author 汤国栋
     * @date 2017年1月24日 上午10:17:03
     */
    public static String generateXml(Map<String, Object> map) {
        try {
            if (map.keySet().size() > 1) {
                throw new RuntimeException("XML只能有一个根节点");
            }
            String rootKey = (String) map.keySet().toArray()[0];
            Element elementRoot = DocumentHelper.createElement(rootKey);
            Document document = DocumentHelper.createDocument(elementRoot);
            Element element = mapToElement((Map<String, Object>) map.get(rootKey), elementRoot);
            return document.asXML();
        } catch (Exception e) {
            throw new RuntimeException("生成XML数据出错");
        }
    }

    /**
     * 遍历生成节点
     *
     * @param map
     * @param element
     * @return
     * @author 汤国栋
     * @date 2017年1月24日 上午10:17:26
     */
    private static Element mapToElement(Map<String, Object> map, Element element) {
        try {
            for (Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() instanceof String || entry.getValue() == "" || entry.getValue() == null) {
                    element.addElement(entry.getKey()).addCDATA((String) entry.getValue());
                } else if (entry.getValue() instanceof List) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) entry.getValue();
                    for (Map<String, Object> m : list) {
                        mapToElement(m, element.addElement(entry.getKey()));
                    }
                } else {
                    mapToElement((Map<String, Object>) entry.getValue(), element.addElement(entry.getKey()));
                }
            }
            return element;
        } catch (Exception e) {
            throw new RuntimeException("生成XML数据遍历生成节点出错");
        }
    }

}
