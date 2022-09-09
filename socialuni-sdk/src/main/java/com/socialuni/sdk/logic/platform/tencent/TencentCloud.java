package com.socialuni.sdk.logic.platform.tencent;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author qinkaiyuan
 * @date 2020-03-14 20:57
 */
@Component
@Slf4j
public class TencentCloud {
    private static String secretId;
    private static String secretKey;
    private static String region;

    @Value("${socialuni.cloud.tencent.cos.secret-id}")
    public void setSecretId(String secretId) {
        TencentCloud.secretId = secretId;
    }

    @Value("${socialuni.cloud.tencent.cos.secret-key}")
    public void setSecretKey(String secretKey) {
        TencentCloud.secretKey = secretKey;
    }

    @Value("${socialuni.cloud.tencent.cos.region}")
    public void setRegion(String region) {
        TencentCloud.region = region;
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
        Credential cred = TencentCloud.getCredential();
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
        Credential cred = TencentCloud.getCredential();
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
        Integer resScore = TencentCloud.imgAuthGetScore(userImg, authImg);
        //50分太高，老有人认证不过，设置为40
        Integer authScore = 40;
        return resScore >= authScore;
    }

    public static Integer imgAuthGetScore(String userImg, String authImg) {
        //新版本更新，userImg照片最多三张。上传非本人照片时，禁止上传
        Credential cred = TencentCloud.getCredential();
        ClientProfile clientProfile = TencentCloud.getClientProfile("iai.tencentcloudapi.com");
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
}
