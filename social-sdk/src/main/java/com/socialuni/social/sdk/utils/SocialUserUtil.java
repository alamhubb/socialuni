package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.SocialuniProviderLoginType;
import com.socialuni.social.exception.SocialNullUserException;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserPhoneDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.sdk.repository.CommonTokenRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import com.socialuni.social.sdk.redis.SocialUserPhoneRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserUtil {
    private static CommonTokenRepository commonTokenRepository;
    private static UserRepository userRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
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
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialUserUtil.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    public static Integer getMineUserIdAllowNull() {
        UserDO user = SocialUserUtil.getMineUserAllowNull();
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();
        return userId;
    }

    public static Integer getMineUserIdAllowNull(UserDO mineUser) {
        //解析token
        if (mineUser == null) {
            return null;
        }
        Integer userId = mineUser.getId();
        return userId;
    }

    public static Integer getMineUserIdNotNull() {
        Integer userId = SocialUserUtil.getMineUserIdAllowNull();
        if (userId == null) {
            throw new SocialNullUserException();
        }
        return userId;
    }

    public static UserDO getMineUser() {
        //解析token
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNullUserException();
        }
        //返回user
        return mineUser;
    }

    public static UserDO getMineUserAllowNull() {
        //解析token
        TokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDO();
        if (tokenDO == null) {
            return null;
        }
        //返回user
        UserDO mineUser = SocialUserUtil.get(tokenDO.getUserId());
        return mineUser;
    }

    //必须有，websocket无法从request中获取token只能传入
    public static UserDO getUserByWebsocketToken(String token) {
        //解析token
        TokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDO(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        UserDO user = SocialUserUtil.get(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        UserDO userDO = SocialUserUtil.getMineUserAllowNull();
        return SocialUserUtil.getUserPhoneNum(userDO.getId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(userId);
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
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(SocialuniProviderLoginType.socialuni, uid);
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
