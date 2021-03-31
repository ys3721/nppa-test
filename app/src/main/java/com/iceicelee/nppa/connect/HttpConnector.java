package com.iceicelee.nppa.connect;

import com.iceicelee.nppa.constants.TestUrlConstants.ReqHttpMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author: Yao Shuai
 * @date: 2021/3/30 12:48
 */
public class HttpConnector {

    public String send(String urlStr, ReqHttpMethod reqMethod, Map<String, String> reqProps, Map<String, String> getParam, String data) throws Exception {
        if (reqMethod.getMethod().equals("GET") && !getParam.isEmpty()) {
            urlStr += "?";
            for (Map.Entry<String, String> entry : getParam.entrySet()) {
                urlStr += (entry.getKey() + "=" + entry.getValue());
            }
        }
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(reqMethod.getMethod());
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("User-Agent", "NppaTester");

        for (Map.Entry<String, String> entry : reqProps.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        connection.connect();
        if (reqMethod.getMethod().equals("POST")) {
            connection.getOutputStream().write(data.getBytes(UTF_8));
        }
        System.out.println(url.toString()+ "send:" + data);
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        connection.disconnect();
        return line;
    }
}
