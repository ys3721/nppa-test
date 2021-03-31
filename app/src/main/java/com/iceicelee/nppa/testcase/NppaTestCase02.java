package com.iceicelee.nppa.testcase;

import com.iceicelee.nppa.Global;
import com.iceicelee.nppa.constants.TestUrlConstants.ReqHttpMethod;
import com.iceicelee.nppa.utils.EncryptUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *  testcase2- 实名认证接口，返回“认证中"表示成功
 *  测试数据使用 ：
 *  {"ai":"200000000000000008",
 *   "name":"某二八"
 *    idNum":"110000190201040013"
 * }
 * 这个接口本质上和testcase01是一样的， 只是传过去的数据不同
 *
 * @author: Yao Shuai
 * @date: 2021/3/31 20:57
 */
public class NppaTestCase02 {
    
    private String ai = "200000000000000008";

    private String name = "某二八";

    private String idNum ="110000190201040013";

    private String testCode;

    public NppaTestCase02(String testCode) {
        this.testCode = testCode;
    }

    public String assembleReqUrl() {
        return Global.getUrlProvider().getTestUrl(1, this.testCode);
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

    private Map<String, String> buildCommonReqHeadMap() {
        Map<String, String> headPropertyMap = new HashMap<>(Global.getConfig().getAppIdAndBizIdMap());
        headPropertyMap.put("timestamps", System.currentTimeMillis() + "");
        return headPropertyMap;
    }


    private ReqHttpMethod getReqMethod() {
        return Global.getUrlProvider().getTestUrlMethod(2);
    }
}
