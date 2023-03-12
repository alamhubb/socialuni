package com.socialuni.social.sdk.logic.factory.RO.user;

import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniGetUserContactRecordDO;
import com.socialuni.social.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.facade.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.logic.factory.UserImgROFactory;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowInfoRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocialuniUserDetailROFactory {
    public static SocialuniUserDetailRO getUserDetailRO(SocialuniUserDo userDO, SocialuniUserDo mineUser) {
        //user基础信息
        SocialuniUserFollowInfoRO socialUserDetailFollowRO = SocialuniUserFollowDetailROFactory.newSocialFollowUserRO(userDO, mineUser);

        //user基础信息
        SocialuniUserDetailRO userDetailVO = new SocialuniUserDetailRO(socialUserDetailFollowRO);

        Integer userUnionId = userDO.getUnionId();

        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getOrCreate(userUnionId);
        userDetailVO.setSchoolName(socialuniUserExpandDo.getSchoolName());
        userDetailVO.setLastOnlineTime(socialuniUserExpandDo.getLastOnlineTime());
        //联系方式需要认证
        //每次设置联系方式
        // 联系方式需要审核，联系方式单独开一张表

        // 设置联系方式，认证过后呢
        //他获取过你的联系方式

        //界面需要显示，未展示和展示，已填写
        //每笔充值消费，都记录下消费后当前数量

        if (mineUser != null) {
            String contactInfo = socialuniUserExpandDo.getContactInfo();
            if (userDetailVO.getIsMine()) {
                userDetailVO.setContactInfo(contactInfo);
                userDetailVO.setOpenContactInfo(socialuniUserExpandDo.getOpenContactInfo());
            } else {
                SocialuniGetUserContactRecordDO socialuniGetUserContactRecordDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUser.getUserId(), userDO.getUserId(), SocialuniGetUserContactRecordDO.class);
                //是否允许展示
                if (socialuniGetUserContactRecordDO != null) {
                    userDetailVO.setOpenContactInfo(true);
                    //如果用户开启获取联系方式，则使用用户的联系方式
                    if (socialuniUserExpandDo.getOpenContactInfo()) {
                        userDetailVO.setContactInfo(socialuniUserExpandDo.getContactInfo());
                    } else {
                        //否则使用记录中的联系方式
                        userDetailVO.setContactInfo(socialuniGetUserContactRecordDO.getContactInfo());
                    }
                } else {
                    //如果用户开启了
                    if (socialuniUserExpandDo.getOpenContactInfo()) {
                        if (StringUtils.isNotEmpty(contactInfo)) {
                            //不为自己且开启了获取，则模糊化
                            contactInfo = contactInfo.charAt(0) + "*****";
                            userDetailVO.setContactInfo(contactInfo);
                        }
                        userDetailVO.setOpenContactInfo(false);
                    } else {
                        userDetailVO.setOpenContactInfo(false);
                    }
                }
            }
        }
        //用户图片
        List<SocialuniUserImgDo> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userDO.getUnionId());
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);
        userDetailVO.setImgs(imgVOS);

        return userDetailVO;
    }

    public static List<SocialuniUserDetailRO> getUserDetailList(List<SocialuniUserDo> userDOs, SocialuniUserDo mineUser) {
        return ListConvertUtil.toList(SocialuniUserDetailROFactory::getUserDetailRO, userDOs, mineUser);
    }
}
