package com.iceicelee.nppa.sign;

import com.iceicelee.nppa.Global;
import com.iceicelee.nppa.utils.EncryptUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Yao Shuai
 * @date: 2021/3/30 14:59
 */
public class SignService {

    /**
     * 字典序列拼起来 然后把data拼到最后
     *
     * @return
     */
    public String sign(Map<String, String> reqProperty, Map<String, String> urlArgs, String encryptedData) {
        HashMap<String, String> allArgs = new HashMap<>();
        if (reqProperty != null && !reqProperty.isEmpty()) {
            allArgs.putAll(reqProperty);
        }
        if (urlArgs != null && !urlArgs.isEmpty()) {
            allArgs.putAll(urlArgs);
        }
        //为了按字典序取出来
        List<String> sortKeys = new ArrayList<>(allArgs.keySet());
        Collections.sort(sortKeys);

        StringBuilder materials = new StringBuilder();
        for (String key : sortKeys) {
            materials.append(key).append(allArgs.get(key));
        }
        materials.append(encryptedData);
        String all = Global.getConfig().getSecretKey() + materials.toString();
        return EncryptUtils.sign(all);
    }

}
