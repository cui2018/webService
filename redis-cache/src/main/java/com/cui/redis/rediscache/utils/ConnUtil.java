package com.cui.redis.rediscache.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 与连接相关的Util类
 */
public class ConnUtil {

    /**
     * UrlEncode， UTF-8 编码
     *
     * @param str 原始字符串
     * @return
     */
    public static String urlEncode(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从HttpURLConnection 获取返回的字符串
     *
     * @param conn
     * @return
     * @throws IOException
     * @throws DemoException
     */
    public static String getResponseString(HttpURLConnection conn) throws IOException, DemoException {
        return new String(getResponseBytes(conn));
    }

    /**
     * 从HttpURLConnection 获取返回的bytes
     * 注意 HttpURLConnection自身问题， 400类错误，会直接抛出异常。不能获取conn.getInputStream();
     *
     * @param conn
     * @return
     * @throws IOException   http请求错误
     * @throws DemoException http 的状态码不是 200
     */
    public static byte[] getResponseBytes(HttpURLConnection conn) throws IOException, DemoException {
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            System.err.println("http 请求返回的状态码错误，期望200， 当前是 " + responseCode);
            if (responseCode == 401) {
                System.err.println("可能是appkey appSecret 填错");
            }
            System.err.println("response headers" + conn.getHeaderFields());
            InputStream inputStream = conn.getErrorStream();
            byte[] result = getInputStreamContent(inputStream);
            System.err.println(new String(result));

            throw new DemoException("http response code is" + responseCode);
        }

        InputStream inputStream = conn.getInputStream();
        byte[] result = getInputStreamContent(inputStream);
        return result;
    }

    /**
     * 将InputStream内的内容全部读取，作为bytes返回
     *
     * @param is
     * @return
     * @throws IOException @see InputStream.read()
     */
    public static byte[] getInputStreamContent(InputStream is) throws IOException {
        byte[] b = new byte[1024];
        // 定义一个输出流存储接收到的数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 开始接收数据
        int len = 0;
        while (true) {
            len = is.read(b);
            if (len == -1) {
                // 数据读完
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String runRawPostMethod(String token, String url2) throws IOException, DemoException {
        //String contentTypeStr = "audio/" + FORMAT + "; rate=" + RATE;
        //System.out.println(url2);
        String contentTypeStr = "";
        byte[] content = getFileContent("");
        HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", contentTypeStr);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(content);
        conn.getOutputStream().close();
        System.out.println("url is " + url2);
        System.out.println("header is  " + "Content-Type :" + contentTypeStr);
        String result = ConnUtil.getResponseString(conn);
        return result;
    }

    public String runJsonPostMethod(String url, JSONObject params) throws DemoException, IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.toString().getBytes());
        conn.getOutputStream().close();
        String result = ConnUtil.getResponseString(conn);
        return result;
    }

    private String base64Encode(byte[] content) {
        /**
         Base64.Encoder encoder = Base64.getEncoder(); // JDK 1.8  推荐方法
         String str = encoder.encodeToString(content);
         **/

        char[] chars = Base64Util.encode(content); // 1.7 及以下，不推荐，请自行跟换相关库
        String str = new String(chars);

        return str;
    }

    private byte[] getFileContent(String filename) throws DemoException, IOException {
        File file = new File(filename);
        if (!file.canRead()) {
            System.err.println("文件不存在或者不可读: " + file.getAbsolutePath());
            throw new DemoException("file cannot read: " + file.getAbsolutePath());
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            return ConnUtil.getInputStreamContent(is);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
