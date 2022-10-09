package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkImgRO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SocialTalkImgROFactory {


    public static SocialuniTalkImgRO newTalkImgRO(SocialuniTalkImgModel socialTalkImgDO) {
        SocialuniTalkImgRO socialTalkImgRO = new SocialuniTalkImgRO();
        socialTalkImgRO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialuniTalkImgRO> newTalkImgROS(List<SocialuniTalkImgModel> imgDOS) {
        return imgDOS.stream().map(SocialTalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
