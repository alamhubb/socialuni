package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.logic.platform.tencent.TencentCloudAPI;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;
import org.apache.commons.lang3.StringUtils;

public class ImgContentUtil {
    public static boolean hasPeopleImg(String imgUrl) {
        TiiaClient client = TencentCloudAPI.getTiiaClient();
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DetectLabelRequest req = new DetectLabelRequest();
        req.setImageUrl(SocialuniSystemConst.getStaticResourceUrl() + imgUrl);
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
