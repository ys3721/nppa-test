package com.iceicelee.nppa.utils;

import com.iceicelee.nppa.constants.TestUrlConstants;
import com.iceicelee.nppa.constants.TestUrlConstants.ReqHttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Yao Shuai
 * @date: 2021/3/26 20:27
 */
public class TestUrlProvider {

    private static Map<Integer, URL2Method> testCaseId2URL;

    static {
        testCaseId2URL = new HashMap<>();
        testCaseId2URL.put(1, new URL2Method(TestUrlConstants.test1Url, ReqHttpMethod.POST));
        testCaseId2URL.put(2, new URL2Method(TestUrlConstants.test2Url, ReqHttpMethod.POST));
        testCaseId2URL.put(3, new URL2Method(TestUrlConstants.test3Url, ReqHttpMethod.POST));
        testCaseId2URL.put(4, new URL2Method(TestUrlConstants.test4Url, ReqHttpMethod.POST));
        testCaseId2URL.put(5, new URL2Method(TestUrlConstants.test5Url, ReqHttpMethod.POST));
        testCaseId2URL.put(6, new URL2Method(TestUrlConstants.test6Url, ReqHttpMethod.POST));
        testCaseId2URL.put(7, new URL2Method(TestUrlConstants.test7Url, ReqHttpMethod.POST));
        testCaseId2URL.put(8, new URL2Method(TestUrlConstants.test8Url, ReqHttpMethod.POST));
    }

    public String getTestUrl(int testNum, String code) {
        return testCaseId2URL.get(testNum).url + code;
    }

    public ReqHttpMethod getTestUrlMethod(int testNum) {
        return testCaseId2URL.get(testNum).httpMethod;
    }

    static class URL2Method {
        public final String url;
        public final ReqHttpMethod httpMethod;

        URL2Method(String url, ReqHttpMethod httpMethod) {
            this.url = url;
            this.httpMethod = httpMethod;
        }
    }
}
