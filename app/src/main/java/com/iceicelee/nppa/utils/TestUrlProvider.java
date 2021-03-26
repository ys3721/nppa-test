package com.iceicelee.nppa.utils;

import com.iceicelee.nppa.constants.TestUrlConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Yao Shuai
 * @date: 2021/3/26 20:27
 */
public class TestUrlProvider {

    private static Map<Integer, String> testCaseId2URL;

    static {
        testCaseId2URL = new HashMap<>();
        testCaseId2URL.put(1, TestUrlConstants.test1Url);
        testCaseId2URL.put(2, TestUrlConstants.test2Url);
        testCaseId2URL.put(3, TestUrlConstants.test3Url);
        testCaseId2URL.put(4, TestUrlConstants.test4Url);
        testCaseId2URL.put(5, TestUrlConstants.test5Url);
        testCaseId2URL.put(6, TestUrlConstants.test6Url);
        testCaseId2URL.put(7, TestUrlConstants.test7Url);
        testCaseId2URL.put(8, TestUrlConstants.test8Url);
    }

    public String getTestUrl(int testNum, String code) {
        return testCaseId2URL.get(testNum) + code;
    }

}
