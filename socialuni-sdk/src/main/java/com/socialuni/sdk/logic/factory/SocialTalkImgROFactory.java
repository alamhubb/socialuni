package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkImgRO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SocialTalkImgROFactory {


    public static SocialuniTalkImgRO newTalkImgRO(SocialuniTalkImgDO socialTalkImgDO) {
        SocialuniTalkImgRO socialTalkImgRO = new SocialuniTalkImgRO();
        socialTalkImgRO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialuniTalkImgRO> newTalkImgROS(List<SocialuniTalkImgDO> imgDOS) {
        return imgDOS.stream().map(SocialTalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
