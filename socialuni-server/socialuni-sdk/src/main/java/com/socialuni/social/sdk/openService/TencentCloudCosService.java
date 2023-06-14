package com.socialuni.social.sdk.openService;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.sdk.dao.DO.TencentCosAuditRecordDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
import com.socialuni.social.web.sdk.dao.utils.ErrorLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TencentCloudCosService {
    @Resource
    TencentCosAuditRecordRepository tencentCosAuditRecordRepository;
    @Resource
    SocialuniUserImgRepository userImgRepository;
    @Resource
    TalkImgRepository talkImgRepository;

    public void setCosImgSocialInfo(TencentCosAuditRecordDO recordDO) {
        //contentType
        String imgUrl = recordDO.getUrl();
        boolean containsTalk = imgUrl.contains("talk");
        String contentType;
        if (containsTalk) {
            contentType = SocialuniContentType.talk;
        } else {
            contentType = SocialuniContentType.user;
        }
        recordDO.setStatus(SocialuniCommonStatus.enable);
        recordDO.setContentType(contentType);
        //thumbType
        String[] imgThumbTypes = imgUrl.split("%21");
        if (imgThumbTypes.length > 1) {
            recordDO.setImgThumbType(imgThumbTypes[1]);
        } else {
            recordDO.setImgThumbType(ImgThumbnailType.original);
        }
        String cosRootUrl = SocialuniSystemConst.getStaticResourceUrl();
        Integer cosRootLength = cosRootUrl.length();
        int subStrLength = 54;
        if (!(imgThumbTypes[0].length() > subStrLength)) {
            return;
        }
        //imgKey,截取掉前边的域名
        String imgKey = imgThumbTypes[0].substring(subStrLength);
        recordDO.setImgKey(imgKey);

        if (contentType.equals(SocialuniContentType.talk)) {
            SocialuniTalkImgDO talkImgDO = talkImgRepository.findFirstBySrc(imgKey);
            if (talkImgDO != null) {
                recordDO.setImgContentId(talkImgDO.getUnionId());
                recordDO.setImgParentContentId(talkImgDO.getTalkId());
                recordDO.setUserId(talkImgDO.getUserId());
            } else {
                //不存在的talk，talk未发布成功
                return;
            }
        } else {
            SocialuniUserImgDo userImgDO = userImgRepository.findFirstBySrc(imgKey);
            if (userImgDO != null) {
                recordDO.setImgContentId(userImgDO.getUnionId());
                recordDO.setImgParentContentId(userImgDO.getUserId());
                recordDO.setUserId(userImgDO.getUserId());
            } else {
                ErrorLogUtil.error("错误的imgKey");
            }
        }
        tencentCosAuditRecordRepository.save(recordDO);
    }
}
