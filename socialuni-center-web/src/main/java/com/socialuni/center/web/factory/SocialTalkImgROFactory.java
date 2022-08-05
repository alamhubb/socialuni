package com.socialuni.center.web.factory;

import com.socialuni.center.web.model.DO.talk.SocialTalkImgDO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkImgRO;
import com.socialuni.center.web.config.SocialAppConfig;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SocialTalkImgROFactory {


    public static SocialTalkImgRO newTalkImgRO(SocialTalkImgDO socialTalkImgDO) {
        SocialTalkImgRO socialTalkImgRO = new SocialTalkImgRO();
        socialTalkImgRO.setId(socialTalkImgDO.getId());

        socialTalkImgRO.setSrc(SocialAppConfig.getStaticResourceUrl()  + socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialTalkImgRO> newTalkImgROS(List<SocialTalkImgDO> imgDOS) {
        return imgDOS.stream().map(SocialTalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
