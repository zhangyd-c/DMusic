/**
 * Copyright [2016-2018] [yadong.zhang]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zhyd.music.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2017年12月15日 上午10:17:14
 * @since 1.0
 */
public class RestClientUtil {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36";
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientUtil.class);

    public static String post(String urlString, String params, Map<String, String> requestHeader) {
        return request("POST", urlString, params, "UTF-8", requestHeader);
    }

    public static String get(String urlString) {
        return get(urlString, null);
    }

    public static String get(String urlString, Map<String, String> requestHeader) {
        return request("GET", urlString, null, "UTF-8", requestHeader);
    }

    /**
     * @param method
     *         请求方法类型
     * @param url
     *         请求链接
     * @param params
     *         请求参数
     * @param encode
     *         编码
     * @param requestHeader
     *         请求头
     * @return 请求结果
     */
    private static String request(String method, String url, String params, String encode, Map<String, String> requestHeader) {
        // 解决因jdk版本问题造成的SSL请求失败的问题
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        final HttpURLConnection connection;
        try {
            connection = openConnection(url);

            connection.setRequestMethod(method);
            if (null != requestHeader) {
                Set<Map.Entry<String, String>> entrySet = requestHeader.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            } else {
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestProperty("Accept-Charset", "utf-8");
                connection.setRequestProperty("User-Agent", USER_AGENT);
            }
            connection.setDoOutput(true);

            if (params != null) {
                final OutputStream outputStream = connection.getOutputStream();
                writeOutput(outputStream, params);
                outputStream.close();
            }

            LOGGER.info("RestClientUtil url: {}, response: {} : {}", url, connection.getResponseCode(), connection.getResponseMessage());

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                return readInput(connection.getInputStream(), encode);
            } else {
                return readInput(connection.getErrorStream(), encode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("RestClientUtil url: {}", url);
        }
        return null;
    }

    private static HttpURLConnection openConnection(final String urlString) throws Exception {
        final URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }

    private static void writeOutput(final OutputStream outputStream, final String xmlData) throws Exception {
        ByteArrayInputStream inputStram = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));

        final byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStram.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
    }

    private static String readInput(final InputStream is, String encode) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
