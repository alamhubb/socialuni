package com.socialuni.social.community.sdk.logic.factory;

import com.socialuni.social.common.api.entity.SocialuniImgBaseDo;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkImgRO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SocialTalkImgROFactory {


    public static <T extends SocialuniImgBaseDo> SocialuniTalkImgRO newTalkImgRO(T socialTalkImgDO) {
        SocialuniTalkImgRO socialTalkImgRO = new SocialuniTalkImgRO();
        socialTalkImgRO.setSrc(SocialuniSystemConst.getStaticResourceUrl() + socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialuniTalkImgRO> newTalkImgROS(List<? extends SocialuniImgBaseDo> imgDOS) {
        return imgDOS.stream().map(SocialTalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
