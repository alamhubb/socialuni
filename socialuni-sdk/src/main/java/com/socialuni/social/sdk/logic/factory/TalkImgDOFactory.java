package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TalkImgDOFactory {
    public static SocialuniTalkImgModel newTalkImgDO(SocialuniImgAddQO talkImgVO) {
        SocialuniTalkImgModel socialTalkImgDO = new SocialuniTalkImgModel();
        Integer talkImgUnionId = SocialuniUnionIdFacede.createTalkImgUnionId();
        socialTalkImgDO.setUnionId(talkImgUnionId);

        socialTalkImgDO.setSrc(talkImgVO.getSrc());
        socialTalkImgDO.setAspectRatio(talkImgVO.getAspectRatio());
        socialTalkImgDO.setSize(talkImgVO.getSize());
        socialTalkImgDO.setContent(talkImgVO.getContent());
        socialTalkImgDO.setQuality(talkImgVO.getQuality());

        socialTalkImgDO.setContentType(SocialuniContentType.talkImg);
        return socialTalkImgDO;
    }

    public static List<SocialuniTalkImgModel> newTalkImgDOS(List<SocialuniImgAddQO> imgVOS) {
        return imgVOS.stream().map(TalkImgDOFactory::newTalkImgDO).collect(Collectors.toList());
    }
}
