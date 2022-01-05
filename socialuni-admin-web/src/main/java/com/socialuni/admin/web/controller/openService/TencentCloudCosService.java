package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.constant.ImgThumbnailType;
import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.repository.UserImgRepository;
import com.socialuni.social.sdk.repository.community.TalkImgRepository;
import com.socialuni.social.web.sdk.utils.ErrorLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TencentCloudCosService {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;
    @Resource
    UserImgRepository userImgRepository;
    @Resource
    TalkImgRepository talkImgRepository;

    public void setCosImgSocialInfo(TencentCosAuditRecordDO recordDO) {
        //contentType
        String imgUrl = recordDO.getUrl();
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
        //imgKey,截取掉前边的域名
        String imgKey = imgThumbTypes[0].substring(cosRootLength);
        recordDO.setImgKey(imgKey);

        if (contentType.equals(ContentType.talk)) {
            SocialTalkImgDO talkImgDO = talkImgRepository.findFirstBySrc(imgKey);
            if (talkImgDO != null) {
                recordDO.setContentImgId(talkImgDO.getId());
                recordDO.setContentId(talkImgDO.getTalkId());
                recordDO.setUserId(talkImgDO.getUserId());
            } else {
                ErrorLogUtil.error("错误的imgKey");
            }
        } else {
            UserImgDO userImgDO = userImgRepository.findFirstBySrc(imgKey);
            if (userImgDO != null) {
                recordDO.setContentImgId(userImgDO.getId());
                recordDO.setContentId(userImgDO.getUserId());
                recordDO.setUserId(userImgDO.getUserId());
            } else {
                ErrorLogUtil.error("错误的imgKey");
            }
        }
        tencentCosAuditRecordRepository.save(recordDO);
    }
}
