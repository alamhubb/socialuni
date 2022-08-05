package com.socialuni.center.web.utils.model;

import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.constant.GenderTypeNumEnum;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.SocialOAuthUserRO;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */

public class UserOAuthROUtil {
    /*public static SocialOAuthUserRO newUserOAuthROByThreeUser(UserDO userDO) {
        SocialOAuthUserRO user = new SocialOAuthUserRO();
        *//*if (StringUtils.isNotEmpty(devKey)) {
            // 秘钥不为空才返回唯一id，因为非开放数据
            user.setUnionId(threeUserDO.getThreeUserId());
        }*//*
        //为什么不做限制了，因为生成的unionid，只有对应的商户才能用
        user.setId(UnionIdDbUtil.createUserUid(userDO.getId()));
        user.setNickname(userDO.getNickname());
        user.setAvatar(userDO.getAvatar());
        user.setGender(GenderTypeNumEnum.getValueByName(userDO.getGender()));
        user.setAge(userDO.getAge());
        user.setCity(userDO.getCity());
//        user.setBirthday(BirthdayAgeUtil.getYearBirthDateByAge(userDO.getAge()));
        return user;
    }*/
}
