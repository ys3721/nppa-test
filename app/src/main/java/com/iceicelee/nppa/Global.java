package com.iceicelee.nppa;

import com.iceicelee.nppa.config.NppaTestConfig;
import com.iceicelee.nppa.connect.HttpConnector;
import com.iceicelee.nppa.sign.SignService;
import com.iceicelee.nppa.utils.TestUrlProvider;

/**
 * @author: Yao Shuai
 * @date: 2021/3/30 20:23
 */
public class Global {

    private static NppaTestConfig config;

    private static SignService signService;

    private static HttpConnector connector;

    private static TestUrlProvider urlProvider;

    public Global() {
        this.initService();
    }

    public void initService() {
        config = new NppaTestConfig();
        signService = new SignService();
        connector = new HttpConnector();
        urlProvider = new TestUrlProvider();
    }

    public static NppaTestConfig getConfig() {
        return config;
    }

    public static void setConfig(NppaTestConfig config) {
        Global.config = config;
    }

    public static SignService getSignService() {
        return signService;
    }

    public static void setSignService(SignService signService) {
        Global.signService = signService;
    }

    public static HttpConnector getConnector() {
        return connector;
    }

    public static void setConnector(HttpConnector connector) {
        Global.connector = connector;
    }

    public static TestUrlProvider getUrlProvider() {
        return urlProvider;
    }

    public static void setUrlProvider(TestUrlProvider urlProvider) {
        Global.urlProvider = urlProvider;
    }
}
