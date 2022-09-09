package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkImgRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SocialTalkImgROFactory {


    public static SocialuniTalkImgRO newTalkImgRO(SocialTalkImgDO socialTalkImgDO) {
        SocialuniTalkImgRO socialTalkImgRO = new SocialuniTalkImgRO();
        String uid = UnionIdDbUtil.getUidByUnionIdNotNull(socialTalkImgDO.getUnionId());
        socialTalkImgRO.setId(uid);

        socialTalkImgRO.setSrc(SocialAppConfig.getStaticResourceUrl()  + socialTalkImgDO.getSrc());
        socialTalkImgRO.setAspectRatio(socialTalkImgDO.getAspectRatio());
        return socialTalkImgRO;
    }


    public static List<SocialuniTalkImgRO> newTalkImgROS(List<SocialTalkImgDO> imgDOS) {
        return imgDOS.stream().map(SocialTalkImgROFactory::newTalkImgRO).collect(Collectors.toList());
    }
}
