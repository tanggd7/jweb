package net.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IO流工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2017年1月24日 上午9:06:15
 */
public final class IOUtil {

    private final static String CHARSET = "UTF-8";

    /**
     * 获取InputStream流中的数据并解析成字符串
     *
     * @param inputStream
     * @return
     * @throws IOException
     * @author 汤国栋
     * @date 2017年1月24日 上午9:09:46
     */
    public static String getInputStreamString(InputStream inputStream, String charset) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String tempLine = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, charset == null || charset == "" ? IOUtil.CHARSET : charset);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((tempLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(tempLine);
            }
        } catch (Exception e) {
            throw new RuntimeException("流转换字符串出错", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("释放流出错", e);
            }
        }
        return stringBuffer.toString();
    }

}
