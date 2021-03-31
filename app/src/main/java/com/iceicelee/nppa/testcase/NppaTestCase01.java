package com.iceicelee.nppa.testcase;

import com.iceicelee.nppa.Global;
import com.iceicelee.nppa.constants.TestUrlConstants.ReqHttpMethod;
import com.iceicelee.nppa.utils.EncryptUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * {"ai":"100000000000000008",
 * * "name":"
 * * 某一八
 * * "idNum":"110000190101040016"
 * }
 * 就用这一组数据跑testcase01吧
 *
 * @author: Yao Shuai
 * @date: 2021/3/31 11:10
 */
public class NppaTestCase01 {

    private String testCode;

    private String ai = "100000000000000008";

    private String name = "某一八1";

    private String idNum = "110000190101040016";

    public NppaTestCase01(String testCode) {
        this.testCode = testCode;
    }

    public String assembleReqUrl() {
        return Global.getUrlProvider().getTestUrl(1, this.testCode);
    }

    private Map<String, String> buildCommonReqHeadMap() {
        Map<String, String> headPropertyMap = new HashMap<>(Global.getConfig().getAppIdAndBizIdMap());
        headPropertyMap.put("timestamps", System.currentTimeMillis() + "");
        return headPropertyMap;
    }

    /**
     * 测试的方法
     */
    public void test() {
        String postData = this.buildEncryptData();
        Map<String, String> reqHeadMap = this.buildCommonReqHeadMap();
        String sign =  Global.getSignService().sign(reqHeadMap, null, postData);
        if (sign == null) {
            //lgo
            return;
        }
        reqHeadMap.put("sign", sign);
        try {
            Global.getConnector().send(this.assembleReqUrl(), this.getReqMethod(), reqHeadMap, null, postData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String buildEncryptData() {
        JSONObject jo = new JSONObject();
        jo.put("ai", ai);
        jo.put("name", name);
        jo.put("idNum", idNum);
        String dataStr = jo.toString();

        byte[] byteSecretKey = EncryptUtils.hexStringToByte(Global.getConfig().getSecretKey());
        String dataContent = EncryptUtils.aesGcmEncrypt(dataStr, byteSecretKey);
        JSONObject postBody = new JSONObject();
        postBody.put("data", dataContent);
        return postBody.toString();

    }


    private ReqHttpMethod getReqMethod() {
        return Global.getUrlProvider().getTestUrlMethod(1);
    }

}
