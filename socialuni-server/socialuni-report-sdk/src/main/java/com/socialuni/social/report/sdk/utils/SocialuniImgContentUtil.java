package com.socialuni.social.report.sdk.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.utils.IOUtils;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.report.sdk.plugins.tencent.TencentCloudAPI;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class SocialuniImgContentUtil {
    public static String getImgTextContent(String imgKey) {
        if (StringUtils.isEmpty(imgKey)) {
            throw new SocialParamsException("图片路径不能为空");
        }
        // 实例化要请求产品的client对象,clientProfile是可选的
        OcrClient client = TencentCloudAPI.getOcrClient();

        // 实例化一个请求对象,每个接口都会对应一个request对象
        GeneralFastOCRRequest req = new GeneralFastOCRRequest();
        req.setImageUrl(SocialuniSystemConst.getStaticResourceUrl() + imgKey);

        GeneralFastOCRResponse resp;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            resp = client.GeneralFastOCR(req);
            TextDetection[] textDetections = resp.getTextDetections();
            for (TextDetection textDetection : textDetections) {
                stringBuilder.append(textDetection.getDetectedText());
            }
            return stringBuilder.toString();
        } catch (TencentCloudSDKException e) {
            return null;
        }
    }

    public static void hasQrCodeByCos(String imgKey) throws IOException {
        if (StringUtils.isEmpty(imgKey)) {
            throw new SocialParamsException("图片路径不能为空");
        }
        GetObjectRequest getObj = TencentCloudAPI.getCosClientGetRequest(imgKey);
        getObj.putCustomQueryParameter("ci-process", "QRcode");
        COSClient cosClient = TencentCloudAPI.getCosClient();
        COSObject cosObject = cosClient.getObject(getObj);
        COSObjectInputStream content = cosObject.getObjectContent();
        String response = IOUtils.toString(content);
    }

    public static boolean hasQrCodeByCloudAPI(String imgKey) {
        try {
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = TencentCloudAPI.getOcrClient();
            // 实例化一个请求对象,每个接口都会对应一个request对象
            QrcodeOCRRequest req = new QrcodeOCRRequest();
            req.setImageUrl(SocialuniSystemConst.getStaticResourceUrl() + imgKey);
            // 返回的resp是一个QrcodeOCRResponse的实例，与请求对象对应
            QrcodeOCRResponse resp = client.QrcodeOCR(req);
            // 输出json格式的字符串回包
            return true;
        } catch (TencentCloudSDKException e) {
            return false;
        }
    }


    public static boolean hasPeopleImg(String imgKey) {
        TiiaClient client = TencentCloudAPI.getTiiaClient();
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DetectLabelRequest req = new DetectLabelRequest();
        req.setImageUrl(SocialuniSystemConst.getStaticResourceUrl() + imgKey);
        // 返回的resp是一个DetectLabelResponse的实例，与请求对象对应
        DetectLabelResponse resp = null;
        try {
            resp = client.DetectLabel(req);
            String res = DetectLabelResponse.toJsonString(resp);
            return res.contains("人");
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return false;
    }


    //得到文件后缀名
    public static String getFileSuffixName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new SocialParamsException("图片路径不能为空");
        }
        if (filePath.indexOf('.') < 0) {
            return ".jpg";
        }
        //如果后缀长度大于6则默认jpg，因为图片都是最长4个，富裕1个，6个
        String fileSuffixName = filePath.substring(filePath.lastIndexOf("."));
        //有可能存在asfasdf.fasdfasd.这种乱格式
        if (fileSuffixName.length() > 6 || fileSuffixName.length() < 2) {
            return ".jpg";
        }
        return fileSuffixName;
    }
}
