package com.socialuni.sdk.factory;

import com.socialuni.sdk.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkImgRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TalkImgROFactory {
    public static SocialTalkImgRO newTalkImgRO(SocialTalkImgDO socialTalkImgDO) {
        SocialTalkImgRO socialTalkImgRO = new SocialTalkImgRO();
        socialTalkImgRO.setId(socialTalkImgDO.getId());
        socialTalkImgRO.setSrc(socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialTalkImgRO> newTalkImgROS(List<SocialTalkImgDO> imgDOS) {
        return imgDOS.stream().map(TalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
