package com.socialuni.admin.web.controller.openService;

import com.socialuni.admin.web.constant.ImgThumbnailType;
import com.socialuni.admin.web.model.DO.TencentCosAuditRecordDO;
import com.socialuni.admin.web.repository.TencentCosAuditRecordRepository;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.repository.UserImgRepository;
import com.socialuni.center.web.repository.community.TalkImgRepository;
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
        recordDO.setStatus(CommonStatus.enable);
        recordDO.setContentType(contentType);
        //thumbType
        String[] imgThumbTypes = imgUrl.split("%21");
        if (imgThumbTypes.length > 1) {
            recordDO.setImgThumbType(imgThumbTypes[1]);
        } else {
            recordDO.setImgThumbType(ImgThumbnailType.original);
        }
        String cosRootUrl = SocialAppConfig.getStaticResourceUrl();
        Integer cosRootLength = cosRootUrl.length();
        int subStrLength = 54;
        if (!(imgThumbTypes[0].length() > subStrLength)) {
            return;
        }
        //imgKey,截取掉前边的域名
        String imgKey = imgThumbTypes[0].substring(subStrLength);
        recordDO.setImgKey(imgKey);

        if (contentType.equals(ContentType.talk)) {
            SocialTalkImgDO talkImgDO = talkImgRepository.findFirstBySrc(imgKey);
            if (talkImgDO != null) {
                recordDO.setContentImgId(talkImgDO.getId());
                recordDO.setContentId(talkImgDO.getContentId());
                recordDO.setUserId(talkImgDO.getUserId());
            } else {
                //不存在的talk，talk未发布成功
                return;
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
