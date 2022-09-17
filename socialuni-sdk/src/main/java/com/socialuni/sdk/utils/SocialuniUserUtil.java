package com.socialuni.sdk.utils;

import com.socialuni.social.web.sdk.config.SocialRequestUserConfig;
import com.socialuni.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.dao.DO.user.*;
import com.socialuni.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.sdk.dao.repository.CommonTokenRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.repository.user.SocialUserAccountRepository;
import com.socialuni.sdk.dao.repository.user.SocialUserViolationRepository;
import com.socialuni.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.web.sdk.exception.SocialNotLoginException;
import com.socialuni.social.web.sdk.exception.SocialNullUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserUtil {
    private static CommonTokenRepository commonTokenRepository;
    private static UserRepository userRepository;
    private static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserAccountRepository socialUserAccountRepository;
    private static SocialUserViolationRepository socialUserViolationRepository;

    private static SocialRequestUserConfig socialRequestUserConfig;

    @Resource
    public void setSocialRequestUserConfig(SocialRequestUserConfig socialRequestUserConfig) {
        SocialuniUserUtil.socialRequestUserConfig = socialRequestUserConfig;
    }

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialuniUserUtil.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

    @Resource
    public void setCommonUserRepository(UserRepository userRepository) {
        SocialuniUserUtil.userRepository = userRepository;
    }

    @Resource
    public void setCommonTokenRepository(CommonTokenRepository commonTokenRepository) {
        SocialuniUserUtil.commonTokenRepository = commonTokenRepository;
    }

    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniUserUtil.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    @Resource
    public void setSocialUserViolationRepository(SocialUserViolationRepository socialUserViolationRepository) {
        SocialuniUserUtil.socialUserViolationRepository = socialUserViolationRepository;
    }


    public static Integer getMineUserIdAllowNull(SocialuniUserDO mineUser) {
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

    public static SocialuniUserDO getMineUserNotNull() {
        //解析token
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        //返回user
        return mineUser;
    }

    //下面都是联盟的
    public static SocialuniUserDO getMineUserNotNull(String token) {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialuniUserDO getMineUserAllowNull() {
        Integer userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialuniUserDO mineUser = SocialuniUserUtil.getUserNotNull(userId);
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    public static Integer getMineUserIdInterceptor() {
        SocialuniUserDO user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static String getMineUserIdStrInterceptor() {
        SocialuniUserDO user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId().toString();
    }

    public static SocialuniUserDO getMineUserInterceptor() {
        SocialuniUserDO user = SocialuniUserUtil.getMineUserAllowNull();
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
    public static SocialuniUserDO getUserByToken(String token) {
        //解析token
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        SocialuniUserDO user = SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        SocialuniUserDO userDO = SocialuniUserUtil.getMineUserAllowNull();
        return SocialuniUserUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = SocialuniUserUtil.getUserPhoneDO(userId);
        if (socialUserPhoneDO == null) {
            return null;
        }
        return socialUserPhoneDO.getPhoneNum();
    }

    public static SocialUserPhoneDO getUserPhoneNumDO(Integer userId) {
        SocialUserPhoneDO socialUserPhoneDO = SocialuniUserUtil.getUserPhoneDO(userId);
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

    public static SocialuniUserDO getUserNotNull(Integer userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        SocialuniUserDO socialUserDO = getAllowNull(userId);
        if (socialUserDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserDO;
    }

    public static SocialuniUserDO getUserByUid(String uid) {
        Integer id = UnionIdDbUtil.getUnionIdByUidNotNull(uid);
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserDO getAllowNull(Integer userId) {
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

    public static SocialuniUserDO getByUid(String uid) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(SocialuniAccountProviderType.socialuni, uid);
        if (socialUserAccountDO == null) {
            throw new SocialNullUserException();
        }
        return SocialuniUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
    }

    public static boolean isMine(SocialuniUserDO mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }
}
