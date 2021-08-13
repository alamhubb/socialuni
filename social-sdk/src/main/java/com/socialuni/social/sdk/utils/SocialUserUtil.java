package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.ProviderLoginType;
import com.socialuni.social.sdk.exception.SocialNullUserException;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.sdk.repository.CommonTokenRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import com.socialuni.social.sdk.store.SocialUserPhoneStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserUtil {
    private static CommonTokenRepository commonTokenRepository;
    private static UserRepository userRepository;
    private static SocialUserPhoneStore socialUserPhoneStore;
    private static SocialUserAccountRepository socialUserAccountRepository;

    @Resource
    public void setCommonUserRepository(UserRepository userRepository) {
        SocialUserUtil.userRepository = userRepository;
    }

    @Resource
    public void setCommonTokenRepository(CommonTokenRepository commonTokenRepository) {
        SocialUserUtil.commonTokenRepository = commonTokenRepository;
    }

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneStore socialUserPhoneStore) {
        SocialUserUtil.socialUserPhoneStore = socialUserPhoneStore;
    }


    public static Integer getMineUserId() {
        UserDO user = SocialUserUtil.getMineUser();
        if (user == null) {
            throw new SocialNullUserException();
        }
        Integer userId = user.getId();
        return userId;
    }

    public static String getMineUserStringId() {
        UserDO user = SocialUserUtil.getMineUser();
        if (user == null) {
            throw new SocialNullUserException();
        }
        Integer userId = user.getId();
        return userId.toString();
    }

    public static UserDO getMineUser() {
        //解析token
        TokenDO tokenDO = CommonTokenUtil.getCommonTokenDO();
        if (tokenDO == null) {
            return null;
        }
        //返回user
        UserDO user = SocialUserUtil.get(tokenDO.getUserId());
        return user;
    }

    //必须有，websocket无法从request中获取token只能传入
    public static UserDO getUserByWebsocketToken(String token) {
        //解析token
        TokenDO tokenDO = CommonTokenUtil.getCommonTokenDO(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        UserDO user = SocialUserUtil.get(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        UserDO userDO = SocialUserUtil.getMineUser();
        return SocialUserUtil.getUserPhoneNum(userDO.getId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneStore.findByUserId(userId);
        if (socialUserPhoneDO == null) {
            return null;
        }
        return socialUserPhoneDO.getPhoneNum();
    }

    /*public static UserDO get(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialNullUserException();
        }
        return UserUtils.get(Integer.valueOf(userId));
    }*/

    public static UserDO get(Integer userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        UserDO commonUserDOOptional = userRepository.findOneById(userId);
        if (commonUserDOOptional == null) {
            throw new SocialNullUserException();
        }
        return commonUserDOOptional;
    }

    public static UserDO getByUid(String uid) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(ProviderLoginType.socialuni, uid);
        if (socialUserAccountDO == null) {
            throw new SocialNullUserException();
        }
        return SocialUserUtil.get(socialUserAccountDO.getUserId());
    }

    public static boolean isMine(UserDO mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getId());
    }
}
