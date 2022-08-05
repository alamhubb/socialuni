package com.socialuni.center.web.utils;

import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.platform.tencent.TencentCloud;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;

public class ImgCheckUtil {
    public static boolean hasPeopleImg(String imgUrl) {
        TiiaClient client = TencentCloud.getTiiaClient();
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DetectLabelRequest req = new DetectLabelRequest();
        req.setImageUrl(SocialAppConfig.getStaticResourceUrl() + imgUrl);
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
}
