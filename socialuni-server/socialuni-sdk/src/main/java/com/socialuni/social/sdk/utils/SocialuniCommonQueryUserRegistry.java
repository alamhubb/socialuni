package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.api.model.user.SocialuniUserFollowInfoRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.sdk.constant.SocialuniCommonQueryKey;
import com.socialuni.social.common.sdk.model.QO.SocialuniKeyQueryQO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.user.sdk.factory.SocialuniUserDetailROFactory;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;


public class SocialuniCommonQueryUserRegistry implements SocialuniCommonQueryRegistry<SocialuniKeyQueryQO> {


    @Override
    public List<String> registryKeys() {
        List<String> keys = ListConvertUtil.objectListToStringList(JavaReflectUtil.getClassStaticFinalFieldValues(SocialuniCommonQueryKey.class));
        return keys;
    }

    @Override
    public void checked(SocialuniKeyQueryQO queryQO) {
        List<String> keys = ListConvertUtil.objectListToStringList(JavaReflectUtil.getClassStaticFinalFieldValues(SocialuniCommonQueryKey.class));
        String userId = queryQO.getUserId();
        if (StringUtils.isEmpty(userId)) {
            throw new SocialBusinessException(MessageFormat.format("查询结果：{}，需要查询参数：{}", keys.toString(), "userId"));
        }
    }

    //需要实现的方法，registryKey
    @Override
    public HashMap<String, Object> registryKeyResults(SocialuniKeyQueryQO queryQO) {
        List<String> keys = ListConvertUtil.objectListToStringList(JavaReflectUtil.getClassStaticFinalFieldValues(SocialuniCommonQueryKey.class));

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserByUuid(queryQO.getUserId());
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialuniUserDetailRO socialuniUserDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(socialuniUserDo, mineUser);

        HashMap<String, Object> hashMap = new HashMap<>();

        for (String key : keys) {
            Object value = null;
            if (SocialuniCommonQueryKey.SocialuniUserRO.equals(key)) {
                value = new SocialuniUserRO(socialuniUserDetailRO);
            } else if (SocialuniCommonQueryKey.SocialuniContentUserRO.equals(key)) {
                value = new SocialuniContentUserRO(socialuniUserDetailRO);
            } else if (SocialuniCommonQueryKey.SocialuniUserFollowInfoRO.equals(key)) {
                value = new SocialuniUserFollowInfoRO(socialuniUserDetailRO);
            } else if (SocialuniCommonQueryKey.user_id.equals(key)) {
                value = socialuniUserDetailRO.getId();
            } else if (SocialuniCommonQueryKey.user_nickname.equals(key)) {
                value = socialuniUserDetailRO.getNickname();
            } else if (SocialuniCommonQueryKey.user_isMine.equals(key)) {
                value = socialuniUserDetailRO.getIsMine();
            } else if (SocialuniCommonQueryKey.user_avatar.equals(key)) {
                value = socialuniUserDetailRO.getAvatar();
            } else if (SocialuniCommonQueryKey.user_gender.equals(key)) {
                value = socialuniUserDetailRO.getGender();
            } else if (SocialuniCommonQueryKey.user_type.equals(key)) {
                value = socialuniUserDetailRO.getType();
            } else if (SocialuniCommonQueryKey.user_age.equals(key)) {
                value = socialuniUserDetailRO.getAge();
            } else if (SocialuniCommonQueryKey.user_city.equals(key)) {
                value = socialuniUserDetailRO.getCity();
            }
            hashMap.put(key, value);
        }
        return hashMap;
    }


}
