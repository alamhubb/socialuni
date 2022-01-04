package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.constant.ImgThumbnailType;
import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.repository.UserImgRepository;
import com.socialuni.social.sdk.repository.community.TalkImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("openService/tencent/cos")
public class TencentCloudCosController {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;
    @Resource
    UserImgRepository userImgRepository;
    @Resource
    TalkImgRepository talkImgRepository;

    @PostMapping("contentAuditCallback")
    public ResultRO<Object> tencentCloudCosContentAuditCallback(@RequestBody TencentCosCallbackResultRO result) {
        System.out.println(result);
        TencentCosCallbackDataRO callbackDataRO = result.getData();
        TencentCosAuditRecordDO recordDO = new TencentCosAuditRecordDO();

        //设置result内容
        recordDO.setCode(result.getCode());
        recordDO.setMessage(result.getMessage());

        //设置data内容
        recordDO.setTraceId(callbackDataRO.getTrace_id());
        recordDO.setForbiddenStatus(callbackDataRO.getForbidden_status());
        recordDO.setEvent(callbackDataRO.getEvent());
        recordDO.setResult(callbackDataRO.getResult());
        recordDO.setUrl(callbackDataRO.getUrl());
        //pornInfo
        TencentCosCallbackDataPornInfo pornInfo = callbackDataRO.getPorn_info();
        recordDO.setHitFlag(pornInfo.getHit_flag());
        recordDO.setScore(pornInfo.getScore());
        recordDO.setLabel(pornInfo.getLabel());

        //contentType
        String imgUrl = callbackDataRO.getUrl();
        boolean containsTalk = imgUrl.contains("talk");
        String contentType;
        if (containsTalk) {
            contentType = ContentType.talk;
        } else {
            contentType = ContentType.user;
        }
        recordDO.setContentType(contentType);
        //thumbType
        String[] imgThumbTypes = imgUrl.split("!");
        if (imgThumbTypes.length > 1) {
            recordDO.setImgThumbType(imgThumbTypes[1]);
        } else {
            recordDO.setImgThumbType(ImgThumbnailType.original);
        }
        String cosRootUrl = SocialAppConfig.getStaticResourceUrl();
        Integer cosRootLength = cosRootUrl.length();
        //imgKey
        String imgKey = imgThumbTypes[0].substring(cosRootLength);
        recordDO.setImgKey(imgKey);

        if (contentType.equals(ContentType.talk)) {
            SocialTalkImgDO talkImgDO = talkImgRepository.findFirstBySrc(imgKey);
            if (talkImgDO != null) {

            } else {
            }
        } else {

        }


        tencentCosAuditRecordRepository.save(recordDO);
        return ResultRO.success();
    }

    public static void main(String[] args) {
        String sfa = "https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/user/d762050513f341c099f082c467e0606e/talk/cc602fe5f8f642fdb948467eceac6d22.jpg%21normal";
        System.out.println(sfa.substring(54));
    }
}
