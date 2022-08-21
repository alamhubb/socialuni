package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.model.DO.user.*;
import com.socialuni.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.repository.CommonTokenRepository;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.sdk.repository.user.SocialUserAccountRepository;
import com.socialuni.sdk.repository.user.SocialUserViolationRepository;
import com.socialuni.sdk.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.constant.UserIdentityAuthStatus;
import com.socialuni.social.exception.SocialNullUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserUtil {
    private static CommonTokenRepository commonTokenRepository;
    private static UserRepository userRepository;
    private static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserAccountRepository socialUserAccountRepository;
    private static SocialUserViolationRepository socialUserViolationRepository;

    private static SocialRequestUserConfig socialRequestUserConfig;

    @Resource
    public void setSocialRequestUserConfig(SocialRequestUserConfig socialRequestUserConfig) {
        SocialUserUtil.socialRequestUserConfig = socialRequestUserConfig;
    }

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialUserUtil.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

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

    @Resource
    public void setSocialUserViolationRepository(SocialUserViolationRepository socialUserViolationRepository) {
        SocialUserUtil.socialUserViolationRepository = socialUserViolationRepository;
    }


    public static Integer getMineUserIdAllowNull(SocialUserDO mineUser) {
        //解析token
        if (mineUser == null) {
            return null;
        }
        Integer userId = mineUser.getUnionId();
        return userId;
    }

    public static Integer getMineUserIdNotNull() {
        return getMineUserNotNull().getUnionId();
    }

    public static String getMineUserUuidIdNotNull() {
        Integer mineUserId = getMineUserIdNotNull();
        return UnionIdDbUtil.getUidByUnionIdNotNull(mineUserId);
    }

    public static SocialUserDO getMineUserNotNull() {
        //解析token
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNullUserException();
        }
        //返回user
        return mineUser;
    }

    //下面都是联盟的
    public static SocialUserDO getMineUserNotNull(String token) {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialUserUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialUserDO getMineUserAllowNull() {
        Integer userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialUserDO mineUser = SocialUserUtil.getUserNotNull(userId);
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    public static Integer getMineUserIdInterceptor() {
        SocialUserDO user = SocialUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static String getMineUserIdStrInterceptor() {
        SocialUserDO user = SocialUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId().toString();
    }

    public static SocialUserDO getMineUserInterceptor() {
        SocialUserDO user = SocialUserUtil.getMineUserAllowNull();
        if (user == null) {
            return null;
        }
        if (user.getStatus().equals(UserStatus.violation)) {
            return null;
        }
        //返回user
        return user;
    }

    //必须有，websocket无法从request中获取token只能传入
    public static SocialUserDO getUserByToken(String token) {
        //解析token
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        SocialUserDO user = SocialUserUtil.getUserNotNull(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        SocialUserDO userDO = SocialUserUtil.getMineUserAllowNull();
        return SocialUserUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = SocialUserUtil.getUserPhoneDO(userId);
        if (socialUserPhoneDO == null) {
            return null;
        }
        return socialUserPhoneDO.getPhoneNum();
    }

    public static SocialUserPhoneDO getUserPhoneNumDO(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = SocialUserUtil.getUserPhoneDO(userId);
        if (socialUserPhoneDO == null) {
            return null;
        }
        return socialUserPhoneDO;
    }

    public static SocialUserPhoneDO getUserPhoneDO(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = socialUserPhoneRedis.findUserPhoneByUserId(userId);
        return socialUserPhoneDO;
    }

    public static SocialUserViolationDO getUserViolationDO(Integer userId) {
        SocialUserViolationDO socialUserViolationDO = socialUserViolationRepository.findOneByUserId(userId);
        return socialUserViolationDO;
    }

    /*public static UserDO get(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialNullUserException();
        }
        return UserUtils.get(Integer.valueOf(userId));
    }*/

    public static SocialUserDO getUserNotNull(Integer userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        SocialUserDO socialUserDO = getAllowNull(userId);
        if (socialUserDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserDO;
    }

    public static SocialUserDO getUserByUid(String uid) {
        Integer id = UnionIdDbUtil.getUserUnionIdByUidNotNull(uid);
        return SocialUserUtil.getUserNotNull(id);
    }

    public static SocialUserDO getAllowNull(Integer userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findOneByUnionId(userId);
    }

    public static Boolean getUserIsIdentityAuth(Integer userId) {
        SocialUserIdentityAuthDO socialUserIdentityAuthDO = socialUserIdentityAuthRepository.findFirstByUserId(userId);
        if (socialUserIdentityAuthDO == null) {
            return false;
        }
        return UserIdentityAuthStatus.authSuccessList.contains(socialUserIdentityAuthDO.getStatus());
    }

    public static SocialUserDO getByUid(String uid) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(SocialuniAccountProviderType.socialuni, uid);
        if (socialUserAccountDO == null) {
            throw new SocialNullUserException();
        }
        return SocialUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
    }

    public static boolean isMine(SocialUserDO mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }
}
