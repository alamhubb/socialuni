package com.socialuni.social.user.sdk.platform.tencent;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.Headers;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.auth.COSSigner;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.region.Region;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.user.sdk.platform.model.SocialCosAuthRO;
import com.tencent.cloud.CosStsClient;
import com.tencentcloudapi.cms.v20190321.CmsClient;
import com.tencentcloudapi.cms.v20190321.models.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.CompareFaceRequest;
import com.tencentcloudapi.iai.v20180301.models.CompareFaceResponse;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2020-03-14 20:57
 */
@Component
@Slf4j
public class TencentCloudAPI {
    private static String secretId;
    private static String secretKey;
    private static String region;
    private static String bucketName;

    @Value("${socialuni.cloud.tencent.cos.secret-id}")
    public void setSecretId(String secretId) {
        TencentCloudAPI.secretId = secretId;
    }

    @Value("${socialuni.cloud.tencent.cos.secret-key}")
    public void setSecretKey(String secretKey) {
        TencentCloudAPI.secretKey = secretKey;
    }

    @Value("${socialuni.cloud.tencent.cos.region}")
    public void setRegion(String region) {
        TencentCloudAPI.region = region;
    }

    @Value("${socialuni.cloud.tencent.cos.bucket}")
    public void setBucketName(String bucketName) {
        TencentCloudAPI.bucketName = bucketName;
    }

    public static SocialCosAuthRO getCosAuthorization(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialParamsException("不存在的用户");
        }
        String uploadImgPath = "user/" + userId + "/";
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

    public static Credential getCredential() {
        return new Credential(secretId, secretKey);
    }

    public static ClientProfile getClientProfile(String endPoint) {
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(endPoint);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return clientProfile;
    }

    private static CmsClient getCmsClient() {
        Credential cred = getCredential();
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("cms.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new CmsClient(cred, region, clientProfile);
    }

    public static OcrClient getOcrClient() {
        Credential cred = TencentCloudAPI.getCredential();
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("ocr.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        return new OcrClient(cred, region, clientProfile);
    }

    public static TiiaClient getTiiaClient() {
        Credential cred = TencentCloudAPI.getCredential();
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tiia.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        return new TiiaClient(cred, region, clientProfile);
    }

    public static boolean textIsViolation(String content) {
//        return false;
        return textIsViolationAPI(content);
    }

    public static boolean textIsViolationAPI(String content) {
        final byte[] textBytes = content.getBytes(StandardCharsets.UTF_8);
        final CmsClient client = getCmsClient();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String textBase64 = encoder.encodeToString(textBytes);
        TextModerationRequest textModerationRequest = new TextModerationRequest();
        textModerationRequest.setContent(textBase64);
        TextModerationResponse resp = null;
        try {
            resp = client.TextModeration(textModerationRequest);
        } catch (TencentCloudSDKException e) {
            log.warn("调用文本内容安全时出错，内容：{}", content);
            e.printStackTrace();
        }
        log.info("腾讯云返回结果：{}", resp);
        //返回null，违规
        if (resp == null) {
            return true;
        }
        TextData respData = resp.getData();
        long evilType = respData.getEvilType();
        //20105：广告引流
        //不等于0 且不为 引流，违规,
        boolean violation = respData.getEvilFlag() != 0 && evilType != TencentEvilType.AD;
        if (violation) {
            log.info("文字内容校验结果违规：{}", content);
            log.info("文字内容校验违规，违规类型：{}，关键词：{}", evilType, Arrays.toString(respData.getKeywords()));
        }
        return violation;
    }

    public static boolean imgIsViolation(byte[] imgBytes) {
        //获取cms客户端
        final CmsClient client = getCmsClient();
        //转base64
        final Base64.Encoder encoder = Base64.getEncoder();
        final String imgBase64 = encoder.encodeToString(imgBytes);
        //构建请求
        ImageModerationRequest req = new ImageModerationRequest();
        req.setFileContent(imgBase64);
        ImageModerationResponse resp = null;
        try {
            //发送请求得到返回
            resp = client.ImageModeration(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        //返回null，违规
        if (resp == null) {
            return true;
        }
        //校验返回内容
        ImageData respData = resp.getData();
//        Logger.logger.info("二维码内容：{}", ImageModerationRequest.toJsonString(respData));
        long evilType = respData.getEvilType();
        //20105：广告引流
        //不等于0 且不为性感，违规,
        boolean violation = respData.getEvilFlag() != 0 && evilType != TencentEvilType.SEXY;
        //违规或者性感，输出返回内容详情
        if (violation || evilType == TencentEvilType.SEXY) {
            if (evilType == TencentEvilType.SEXY) {
                log.info("图像内容性感：{}", ImageModerationRequest.toJsonString(respData.getHotDetect()));
            } else {
                log.info("图像校验结果违规，违规类型：{}", evilType);
                if (evilType == TencentEvilType.DORN) {
                    log.info("图像内容色情：{}", ImageModerationRequest.toJsonString(respData.getPornDetect()));
                }
            }
        }
        return violation;
    }


    public static boolean imgAuth(String userImg, String authImg) {
        Integer resScore = TencentCloudAPI.imgAuthGetScore(userImg, authImg);
        //50分太高，老有人认证不过，设置为40
        Integer authScore = 40;
        return resScore >= authScore;
    }

    public static Integer imgAuthGetScore(String userImg, String authImg) {
        //新版本更新，userImg照片最多三张。上传非本人照片时，禁止上传
        Credential cred = TencentCloudAPI.getCredential();
        ClientProfile clientProfile = TencentCloudAPI.getClientProfile("iai.tencentcloudapi.com");
        IaiClient client = new IaiClient(cred, region, clientProfile);
        CompareFaceRequest req = new CompareFaceRequest();
        req.setFaceModelVersion("3.0");
        req.setUrlA(userImg);
        req.setUrlB(authImg);
        CompareFaceResponse resp;
        try {
            resp = client.CompareFace(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return 0;
        }
        Double scoreDb = Math.ceil(resp.getScore());
        return scoreDb.intValue();
    }

    //init cos client
    public static COSClient getCosClient() {
        //https://cloud.tencent.com/document/product/436/10199#.E5.88.9D.E5.A7.8B.E5.8C.96.E5.AE.A2.E6.88.B7.E7.AB.AF
// 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = TencentCloudAPI.secretId;
        String secretKey = TencentCloudAPI.secretKey;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(TencentCloudAPI.region);
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    public static GetObjectRequest getCosClientGetRequest(String imgKey) {
        // 实例化要请求产品的client对象,clientProfile是可选的
        GetObjectRequest getObj = new GetObjectRequest(bucketName, imgKey);
        return getObj;
    }

    //init cos client
    public static String getCosSign(String imgKey) {
        //https://cloud.tencent.com/document/product/436/10199#.E5.88.9D.E5.A7.8B.E5.8C.96.E5.AE.A2.E6.88.B7.E7.AB.AF
// 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = TencentCloudAPI.secretId;
        String secretKey = TencentCloudAPI.secretKey;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(TencentCloudAPI.region);
        ClientConfig clientConfig = new ClientConfig(region);

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = TencentCloudAPI.bucketName;

// 对象键(Key)是对象在存储桶中的唯一标识。详情请参见 [对象键](https://cloud.tencent.com/document/product/436/13324)
//若key不是以“/”开头，则需要在key的开头加上“/”，否则直接resource_path=key
        String resource_path = "/" + imgKey;

// 用来生成签名
        COSSigner signer = new COSSigner();
// 设置签名过期时间(可选)，若未进行设置，则默认使用 ClientConfig 中的签名过期时间(1小时)
// 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);

// 填写本次请求的参数
        Map<String, String> params = new HashMap<String, String>();

// 填写本次请求的头部
        Map<String, String> headers = new HashMap<String, String>();
// host 必填
        headers.put(Headers.HOST, clientConfig.getEndpointBuilder().buildGeneralApiEndpoint(bucketName));

// 请求的 HTTP 方法，上传请求用 PUT，下载请求用 GET，删除请求用 DELETE
        HttpMethodName method = HttpMethodName.GET;

        String sign = signer.buildAuthorizationStr(method, resource_path, headers, params, cred, expirationDate, true);
        return sign;
    }
}
