package com.socialuni.social.expand.factory;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.user.SocialuniUserExtendDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeManage;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserExtendFriendLogDo;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.common.sdk.utils.PositionUtil;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.utils.DistrictStoreUtils;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.factory.UserImgROFactory;
import com.socialuni.social.userImg.utils.SocialuniUserImgDOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SocialuniUserExtendDetailROFactory {
    public static SocialuniUserLikeManage socialuniUserLikeManage;

    @Resource
    public void setSocialuniUserLikeManage(SocialuniUserLikeManage socialuniUserLikeManage) {
        SocialuniUserExtendDetailROFactory.socialuniUserLikeManage = socialuniUserLikeManage;
    }

    public static SocialuniUserExtendDetailRO getUserExtendDetailRO(SocialuniUserDo userDO, SocialuniUserDo mineUser) {
        //user基础信息
        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserExtendDetailRO socialuniUserExtendDetailRO = new SocialuniUserExtendDetailRO(socialuniUserRO);

        //获取用户扩列记录
        SocialuniUserExtendFriendLogDo socialuniUserExtendFriendLogDo = SocialuniUserRepositoryFacede.findByUserId(userDO.getUserId(), SocialuniUserExtendFriendLogDo.class);

        if (socialuniUserExtendFriendLogDo == null) {
            //生成用户扩列记录
            socialuniUserExtendFriendLogDo = SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog(userDO);
        }

//        if (socialuniUserExtendFriendLogDo == null) {
//            throw new SocialParamsException("不该为空");
//        }

        String adCode = socialuniUserExtendFriendLogDo.getAdCode();
        if (StringUtils.isEmpty(adCode)) {
            throw new SocialParamsException("不该为空");
        }

        SocialuniDistrictDO districtDO = DistrictStoreUtils.findFirstOneByAdCode(adCode);


        socialuniUserExtendDetailRO.setCityName(districtDO.getCityName());
        socialuniUserExtendDetailRO.setProvinceName(districtDO.getProvinceName());
        socialuniUserExtendDetailRO.setDistrictName(districtDO.getDistrictName());


        String lon = socialuniUserExtendFriendLogDo.getLon();
        String lat = socialuniUserExtendFriendLogDo.getLat();

        SocialuniRectangleRO dataRO = new SocialuniRectangleRO(Double.valueOf(lon), Double.valueOf(lat));

        Double getDistance = PositionUtil.getDistance(dataRO);

        socialuniUserExtendDetailRO.setDistance(getDistance);

        //用户图片
        List<SocialuniUserImgDo> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        socialuniUserExtendDetailRO.setImgs(imgVOS);


        if (mineUser == null) {
            socialuniUserExtendDetailRO.setHasUserLike(false);
        } else {
            boolean hasUserLike = socialuniUserLikeManage.hasUserLike(mineUser.getUserId(), userDO.getUserId());
            socialuniUserExtendDetailRO.setHasUserLike(hasUserLike);
        }



        return socialuniUserExtendDetailRO;
    }

    public static List<SocialuniUserExtendDetailRO> getUserExtendDetailList(List<SocialuniUserDo> userDOs, SocialuniUserDo mineUser) {
        return ListConvertUtil.toList(SocialuniUserExtendDetailROFactory::getUserExtendDetailRO, userDOs, mineUser);
    }
}
