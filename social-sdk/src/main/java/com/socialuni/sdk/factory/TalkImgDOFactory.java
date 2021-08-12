package com.socialuni.sdk.factory;

import com.socialuni.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkImgAddQO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TalkImgDOFactory {
    public static SocialTalkImgDO newTalkImgDO(SocialTalkImgAddQO talkImgVO) {
        SocialTalkImgDO socialTalkImgDO = new SocialTalkImgDO();
        socialTalkImgDO.setSrc(talkImgVO.getSrc());
        socialTalkImgDO.setAspectRatio(talkImgVO.getAspectRatio());
        socialTalkImgDO.setSize(talkImgVO.getSize());
        socialTalkImgDO.setQuality(talkImgVO.getQuality());
        return socialTalkImgDO;
    }

    public static List<SocialTalkImgDO> newTalkImgDOS(List<SocialTalkImgAddQO> imgVOS) {
        return imgVOS.stream().map(TalkImgDOFactory::newTalkImgDO).collect(Collectors.toList());
    }
}
