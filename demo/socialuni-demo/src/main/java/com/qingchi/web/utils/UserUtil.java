package com.qingchi.web.utils;


import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.repsotiory.UserDetailRepository;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.exception.SocialNullUserException;
import com.socialuni.social.sdk.store.SocialUserAccountStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserUtil {
    static UserDetailRepository userDetailRepository;
    static SocialUserAccountStore socialUserAccountStore;

    @Resource
    public void setUserDetailRepository(UserDetailRepository userDetailRepository) {
        UserUtil.userDetailRepository = userDetailRepository;
    }

    @Resource
    public void setSocialUserAccountStore(SocialUserAccountStore socialUserAccountStore) {
        UserUtil.socialUserAccountStore = socialUserAccountStore;
    }

    public static UserDetailDO getUserDetail(Integer userId) {
        UserDetailDO userDetailDO = userDetailRepository.findOneByUserId(userId);
        if (userDetailDO == null) {
            throw new SocialNullUserException();
        }
        return userDetailDO;
    }

    public static Integer getUserIdByUnionId(String uid) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountStore.getSocialAccountByUnionId(uid);
        if (socialUserAccountDO != null) {
            return socialUserAccountDO.getUserId();
        }
        return null;
    }
}
