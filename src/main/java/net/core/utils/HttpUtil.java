package net.core.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;


/**
 * Http工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2017年1月6日 下午12:25:38
 */
public final class HttpUtil {

    private final static String CHARSET = "UTF-8";

    private final static String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    private final static String CONTENT_TYPE_JSON = "application/json";
    private final static String CONTENT_TYPE_UPLOAD = "multipart/form-data";

    /**
     * 发送get请求
     *
     * @param url
     * @param charset
     * @return
     */
    public static String sendGet(String url, String charset) {
        try {
            URL URL = new URL(url);
            URLConnection connection = URL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestProperty("Accept-Charset", charset == "" || charset == null ? CHARSET : charset);
            httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_FORM);
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new RuntimeException("HTTP，GET请求失败，返回码是：" + httpURLConnection.getResponseCode());
            }

            // 获取请求回调
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer;
            String tempLine;
            try {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, charset == "" || charset == null ? CHARSET : charset);
                bufferedReader = new BufferedReader(inputStreamReader);
                stringBuffer = new StringBuffer();
                while ((tempLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempLine);
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException("发送get请求出错", e);
        }
    }

    public static String sendGet(String url) {
        return sendGet(url, CHARSET);
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param charset     编码格式
     * @param contentType 参数体类型
     * @param param       参数
     * @return
     */
    public static String sendPost(String url, String charset, String contentType, String param) {
        try {
            // 设置http请求头信息
            URL URL = new URL(url);
            URLConnection connection = URL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept-Charset", charset == "" || charset == null ? CHARSET : charset);
            httpURLConnection.setRequestProperty("Content-Type", contentType == "" || contentType == null ? CONTENT_TYPE_FORM : contentType);
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));

            OutputStream outputStream = null;
            OutputStreamWriter outputStreamWriter = null;
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer();
            String tempLine;
            try {
                // 发送参数
                outputStream = httpURLConnection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, charset == "" || charset == null ? CHARSET : charset);
                outputStreamWriter.write(param);
                outputStreamWriter.flush();
                if (httpURLConnection.getResponseCode() >= 300) {
                    throw new Exception("HTTP，POST请求失败，返回码是：" + httpURLConnection.getResponseCode());
                }
                // 获取请求回调
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, charset == "" || charset == null ? CHARSET : charset);
                bufferedReader = new BufferedReader(inputStreamReader);
                while ((tempLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempLine);
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException("发送post请求出错", e);
        }
    }

    public static String sendFormPost(String url, String param) {
        return sendPost(url, CHARSET, CONTENT_TYPE_FORM, param);
    }

    public static String sendFormPost(String url, String charset, String param) {
        return sendPost(url, charset, CONTENT_TYPE_FORM, param);
    }

    public static String sendJsonPost(String url, String json) {
        return sendPost(url, CHARSET, CONTENT_TYPE_JSON, json);
    }

    public static String sendJsonPost(String url, String charset, String json) {
        return sendPost(url, charset, CONTENT_TYPE_JSON, json);
    }

    /**
     * 模拟form表单上传
     *
     * @param url
     * @param charset
     * @param paramMap
     * @param media
     * @param fileName
     * @param fileType
     * @param fileInputStream
     * @return
     */
    public static String sendUploadPost(String url, String charset, Map<String, Object> paramMap, String media, String fileName, String fileType, InputStream fileInputStream) {
        String uuid = UUID.randomUUID().toString();
        String end = "\r\n";
        String partLine = "--";
        String boundary = "----" + uuid;
        try {
            URL URL = new URL(url);
            URLConnection connection = URL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Accept-Charset", charset == "" || charset == null ? CHARSET : charset);
            httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_UPLOAD + "; boundary=" + boundary);

            StringBuffer sb = new StringBuffer();
            OutputStream outputStream = null;
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer();
            String tempLine = null;
            try {
                outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                for (Entry<String, Object> entry : paramMap.entrySet()) {
                    sb.append(partLine + boundary + end);
                    sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + end);
                    sb.append(end);
                    sb.append(entry.getValue());
                    sb.append(end);
                }

                sb.append(partLine + boundary + end);
                sb.append("Content-Disposition: form-data; " + "name=\"" + media + "\";" + "filename=\"" + fileName + "\"" + end);
                sb.append("Content-Type: " + fileType + end);
                sb.append(end);
                outputStream.write(sb.toString().getBytes());
                byte[] b = new byte[1024];
                int length;
                while ((length = fileInputStream.read(b)) != -1) {
                    outputStream.write(b, 0, length);
                }
                outputStream.write(end.getBytes());
                outputStream.write((partLine + boundary + partLine + end).getBytes());
                outputStream.write(end.getBytes());
                outputStream.flush();

                if (httpURLConnection.getResponseCode() >= 300) {
                    throw new Exception("HTTP，POST请求失败，返回码是：" + httpURLConnection.getResponseCode());
                }

                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, HttpUtil.CHARSET);
                bufferedReader = new BufferedReader(inputStreamReader);
                while ((tempLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(tempLine);
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException("发送上传post请求出错", e);
        }
    }

    public static String sendUploadPost(String url, String media, String fileName, String fileType, InputStream fileInputStream) {
        return sendUploadPost(url, CHARSET, new HashMap<String, Object>(), media, fileName, fileType, fileInputStream);
    }

    /**
     * 转换参数
     *
     * @param map
     * @return
     */
    public static String convertParam(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry<String, Object> entry : map.entrySet()) {
            stringBuffer.append("&" + entry.getKey() + "=" + entry.getValue());
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.delete(0, 1);
        }
        return stringBuffer.toString();
    }

}
