package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserDOUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.utils.GenderUtil;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialEditUserDomain {
    @Resource
    SocialuniUserRepository userApi;

    public SocialuniUserRO editUser(SocialUserEditQO socialUserEditQO, SocialuniUserDo mineUser) {
        //昵称
        String nickname = socialUserEditQO.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new SocialBusinessException("昵称不能为空");
        } else {
            String oldNickname = mineUser.getNickname();
            //新旧昵称不一样，则更新
            if (!nickname.equals(oldNickname)) {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(nickname);
                mineUser.setNickname(nickname);
            }
        }

        String editGender = socialUserEditQO.getGender();

        GenderUtil.checkUserEditGenderType(editGender);

        mineUser.setGender(editGender);

        //生日，年龄
        String birthday = socialUserEditQO.getBirthday();
        if (StringUtils.isNotEmpty(birthday)) {
            mineUser.setBirthday(birthday);
            int age = BirthdayAgeUtil.getAgeByBirth(birthday);
            mineUser.setAge(age);
        }

        String userCity = socialUserEditQO.getCity();
        //保存地区名
        if (StringUtils.isNotEmpty(userCity)) {
            String oldCity = mineUser.getCity();
            //新旧昵称不一样，则更新
            if (!userCity.equals(oldCity)) {
                SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(userCity);
                mineUser.setCity(userCity);
            }
        }
        mineUser.setUpdateTime(new Date());

        mineUser = SocialuniUserDOUtil.save(mineUser);

        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(mineUser, mineUser);
        return socialuniUserRO;
    }


    public SocialuniUserRO addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO, SocialuniUserDo mineUser) {

        SocialuniTextContentUtil.validateImg(socialUserImgAddQO, mineUser);

        mineUser.setAvatar(SocialuniSystemConst.getStaticResourceUrl() + socialUserImgAddQO.getSrc() + "!avatar");

        userApi.savePut(mineUser);

        SocialuniUserRO socialMineUserDetailRO = SocialuniUserROFactory.getUserRO(mineUser, mineUser);

        return socialMineUserDetailRO;
    }
}
