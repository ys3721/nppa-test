package com.iceicelee.nppa;

import com.iceicelee.nppa.config.NppaTestConfig;

/**
 * @author: Yao Shuai
 * @date: 2021/3/30 20:23
 */
public class Global {

    private static NppaTestConfig config;

    public Global() {
        this.initService();
    }

    public void initService() {
        config = new NppaTestConfig();
    }

    public static NppaTestConfig getConfig() {
        return config;
    }

    public static void setConfig(NppaTestConfig config) {
        Global.config = config;
    }
}
