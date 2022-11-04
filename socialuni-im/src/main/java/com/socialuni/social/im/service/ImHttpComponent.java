package com.socialuni.social.im.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.im.model.ImInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 封装请求opemIm的工具类。用于公共的逻辑处理。
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:04
 * @since 1.0
 */
@Component
public class ImHttpComponent {
    @Value("${socialuni.openIm.ip:http://82.157.32.169:10002/}")
    private String openImUrl;
    @Value("${socialuni.openIm.platform:1}")
    private int platform = 1;
    @Value("${socialuni.openIm.secret:tuoyun}")
    private String secret;


    public String post(String uri, ImInterface obj){
        return this.post( uri ,new JSONObject(obj));
    }
    public String post(String uri, JSONObject jsonObject){
        fillRequestBody(jsonObject);
        return HttpUtil.post(getRequestUrl(uri),jsonObject.toString());
    }
    /**
     * 解析Response的响应体的内容。
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T parseResponse(String str,Class<T> tClass){
        JSONObject responsesJson = JSONUtil.parseObj(str);
        Integer errCode = responsesJson.getInt("errCode");
        String errMsg = responsesJson.getStr("errMsg");
        switch (errCode){
            case 0:
                return responsesJson.getBean("data",tClass);
            case 400:
            case 500:
            default:
                throw new RuntimeException(errMsg);
        }
    }
    /**
     * 获得完整请求的url
     * @param uri
     * @return
     */
    public String getRequestUrl(String uri){
        return this.openImUrl + uri;
    }

    /**
     * 填充openIm需要的公共字段
     * @param jsonObject
     */
    public void fillRequestBody(JSONObject jsonObject){
        // 请求的平台。
        jsonObject.set("platform",platform);
        // 秘钥。
        jsonObject.set("secret",secret);
        // 操作码。
        jsonObject.set("operationID", RandomUtil.randomString(10));
    }
}
