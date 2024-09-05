package com.socialuni.social.community.sdk.logic.factory;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.utils.SnowflakeIdUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TalkImgDOFactory {
    public static SocialuniTalkImgDO newTalkImgDO(SocialuniImgAddQO talkImgVO) {
        SocialuniTalkImgDO socialTalkImgDO = new SocialuniTalkImgDO();
        Long talkImgUnionId = SocialuniUnionIdFacede.createTalkImgUnionId(SnowflakeIdUtil.nextIdStr());
        socialTalkImgDO.setUnionId(talkImgUnionId);

        socialTalkImgDO.setSrc(talkImgVO.getSrc());
        socialTalkImgDO.setAspectRatio(talkImgVO.getAspectRatio());
        socialTalkImgDO.setSize(talkImgVO.getSize());
        socialTalkImgDO.setContent(talkImgVO.getContent());
        socialTalkImgDO.setQuality(talkImgVO.getQuality());

        socialTalkImgDO.setContentType(SocialuniContentType.talkImg);
        return socialTalkImgDO;
    }

    public static List<SocialuniTalkImgDO> newTalkImgDOS(List<SocialuniImgAddQO> imgVOS) {
        return imgVOS.stream().map(TalkImgDOFactory::newTalkImgDO).collect(Collectors.toList());
    }
}
