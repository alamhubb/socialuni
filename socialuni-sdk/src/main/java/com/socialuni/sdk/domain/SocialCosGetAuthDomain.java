package com.socialuni.sdk.domain;

import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import com.socialuni.social.web.sdk.utils.JsonUtil;
import com.socialuni.sdk.model.RO.app.SocialCosAuthRO;
import com.tencent.cloud.CosStsClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TreeMap;

@Service
public class SocialCosGetAuthDomain {
    @Value("${socialuni.cloud.tencent.cos.secret-id}")
    private String secretId;
    @Value("${socialuni.cloud.tencent.cos.secret-key}")
    private String secretKey;
    @Value("${socialuni.cloud.tencent.cos.region}")
    private String region;
    @Value("${socialuni.cloud.tencent.cos.bucket-name}")
    private String bucketName;

    public SocialCosAuthRO getCosAuthorization(String uploadImgPath) {
        if (uploadImgPath == null) {
            throw new SocialParamsException("图片上传地址不存在");
        }
        TreeMap<String, Object> config = new TreeMap<>();
        // 替换为您的 SecretId
        config.put("SecretId", secretId);
        // 替换为您的 SecretKey
        config.put("SecretKey", secretKey);

        // 临时密钥有效时长，单位是秒，默认1800秒，最长可设定有效期为7200秒
        config.put("durationSeconds", 1800);

        // 换成 bucket 所在地区
        config.put("region", region);
        // 换成您的 bucket
        config.put("bucket", bucketName);

        // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
        // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。

        config.put("allowPrefix", uploadImgPath + "*");

        // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/DOcument/product/436/31923
        String[] allowActions = new String[]{
                // 表单上传、小程序上传
                "name/cos:PutObject",
                "name/cos:PostObject",
                "name/cos:GetObject"

        };
        config.put("allowActions", allowActions);

        try {
            JSONObject credential = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            SocialCosAuthRO cosRo = JsonUtil.objectMapper.readValue(credential.toString(), SocialCosAuthRO.class);
            cosRo.setBucket(bucketName);
            cosRo.setRegion(region);
            cosRo.setUploadImgPath(uploadImgPath);

            return cosRo;
        } catch (IOException e) {
            throw new SocialSystemException(e.getMessage());
        }
    }
}
