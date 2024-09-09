package com.socialuni.social.userImg.factory;

import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.user.SocialuniUserShowRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserShowROFactory;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.model.SocialuniUserImgComRO;
import com.socialuni.social.userImg.utils.SocialuniUserImgDOUtil;

import java.util.List;

public class SocialuniUserImgComROFactory {

    public static SocialuniUserImgComRO getUserImgComRO(SocialuniUserDo userDO) {
        //user基础信息
        SocialuniUserShowRO socialuniUserRO = SocialuniUserShowROFactory.getUserRO(userDO, null);

        //user基础信息
        SocialuniUserImgComRO socialuniUserImgComRO = new SocialuniUserImgComRO(socialuniUserRO);

        //用户图片
        List<SocialuniUserImgDo> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        socialuniUserImgComRO.setImgs(imgVOS);

        return socialuniUserImgComRO;
    }

    public static List<SocialuniUserImgComRO> getUserImgComROList(List<SocialuniUserDo> userDOs) {
        return ListConvertUtil.toList(SocialuniUserImgComROFactory::getUserImgComRO, userDOs);
    }
}
