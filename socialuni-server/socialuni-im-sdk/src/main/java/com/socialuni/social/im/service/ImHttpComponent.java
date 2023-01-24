package com.socialuni.social.im.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.im.constant.OpenImPlatformType;
import com.socialuni.social.im.model.ImInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装请求opemIm的工具类。用于公共的逻辑处理。
 *
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:04
 * @since 1.0
 */
@Component
@Slf4j
public class ImHttpComponent {
    @Value("${socialuni.open-im-server-url:https://im.socialuni.cn:50002}")
    private String openImUrl;
    /* @Value("${socialuni.openIm.platform:1}")
     private int platform = 1;*/
    @Value("${socialuni.open-im-secret:tuoyun}")
    private String secret;

    public String post(String uri, ImInterface obj) {
        return this.post(uri, new JSONObject(obj), null);
    }

    public String post(String uri, ImInterface obj, String token) {
        return this.post(uri, new JSONObject(obj), token);
    }

    public String post(String uri, JSONObject jsonObject, String token) {
        fillRequestBody(jsonObject);
        // 添加请求头信息
        Map<String, String> heads = new HashMap<>();
// 使用json发送请求，下面的是必须的
        heads.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        if(StrUtil.isNotBlank(token)){
            heads.put("token", token);
        }

        String url = getRequestUrl(uri);

        HttpRequest httpRequest = HttpRequest.post(url).headerMap(heads,true)
                .timeout(10000)
                .body(jsonObject.toString());
        log.info("openIm请求详情：{}", httpRequest);

        String body = httpRequest.execute()
                .body();

        log.info("openIm请求返回：{}", body);
        return body;

    }

    /**
     * 解析Response的响应体的内容。
     *
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T parseResponse(String str, Class<T> tClass) {
        JSONObject responsesJson = JSONUtil.parseObj(str);
        Integer errCode = responsesJson.getInt("errCode");
        String errMsg = responsesJson.getStr("errMsg");
        switch (errCode) {
            case 0:
                return responsesJson.getBean("data", tClass);
            case 400:
            case 500:
            default:
                throw new RuntimeException(errMsg);
        }
    }

    /**
     * 获得完整请求的url
     *
     * @param uri
     * @return
     */
    public String getRequestUrl(String uri) {
        return this.openImUrl + uri;
    }

    /**
     * 填充openIm需要的公共字段
     *
     * @param jsonObject
     */
    public void fillRequestBody(JSONObject jsonObject) {
        // 请求的平台。
        jsonObject.set("platform", OpenImPlatformType.getOpenImPlatformType(RequestUtil.getPlatform(), RequestUtil.getSystem()));
        // 秘钥。
        jsonObject.set("secret", secret);
        // 操作码。
        jsonObject.set("operationID", RandomUtil.randomString(10));
    }
}
