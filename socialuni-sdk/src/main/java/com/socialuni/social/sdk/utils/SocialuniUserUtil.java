package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.CommonTokenRepository;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.api.SocialUserViolationInterface;
import com.socialuni.social.user.sdk.api.UserApi;
import com.socialuni.social.sdk.dao.repository.user.SocialUserAccountRepository;
import com.socialuni.social.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.sdk.dao.utils.SocialTokenDOUtil;
import com.socialuni.social.sdk.dao.DO.user.*;
import com.socialuni.social.tance.sdk.api.SocialRequestUserConfig;
import com.socialuni.social.common.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.exception.exception.SocialNullUserException;
import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
import com.socialuni.social.user.sdk.model.SocialUserViolationModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserUtil {
    private static CommonTokenRepository commonTokenRepository;
    private static UserApi userApi;
    private static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserAccountRepository socialUserAccountRepository;
    private static SocialUserViolationInterface socialUserViolationApi;

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
    public void setCommonUserRepository(UserApi userApi) {
        SocialuniUserUtil.userApi = userApi;
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
    public void setSocialUserViolationRepository(SocialUserViolationInterface socialUserViolationApi) {
        SocialuniUserUtil.socialUserViolationApi = socialUserViolationApi;
    }


    public static Integer getMineUserIdAllowNull(SocialuniUserModel mineUser) {
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
        return SocialuniUnionIdFacede.getUuidByUnionIdNotNull(mineUserId);
    }

    public static SocialuniUserModel getMineUserNotNull() {
        //解析token
        SocialuniUserModel mineUser = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        //返回user
        return mineUser;
    }

    //下面都是联盟的
    public static SocialuniUserModel getMineUserNotNull(String token) {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialuniUserModel getMineUserAllowNull() {
        Integer userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialuniUserModel mineUser = SocialuniUserUtil.getUserNotNull(userId);
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    public static Integer getMineUserIdInterceptor() {
        SocialuniUserModel user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static String getMineUserIdStrInterceptor() {
        SocialuniUserModel user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId().toString();
    }

    public static SocialuniUserModel getMineUserInterceptor() {
        SocialuniUserModel user = SocialuniUserUtil.getMineUserAllowNull();
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
    public static SocialuniUserModel getUserByToken(String token) {
        //解析token
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        SocialuniUserModel user = SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        SocialuniUserModel userDO = SocialuniUserUtil.getMineUserAllowNull();
        return SocialuniUserUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneModel socialUserPhoneModel = SocialuniUserUtil.getUserPhoneDO(userId);
        if (socialUserPhoneModel == null) {
            return null;
        }
        return socialUserPhoneModel.getPhoneNum();
    }

    public static SocialUserPhoneModel getUserPhoneNumDO(Integer userId) {
        SocialUserPhoneModel socialUserPhoneModel = SocialuniUserUtil.getUserPhoneDO(userId);
        if (socialUserPhoneModel == null) {
            return null;
        }
        return socialUserPhoneModel;
    }

    public static SocialUserPhoneModel getUserPhoneDO(Integer userId) {
        SocialUserPhoneModel socialUserPhoneModel = socialUserPhoneRedis.findUserPhoneByUserId(userId);
        return socialUserPhoneModel;
    }

    public static SocialUserViolationModel getUserViolationDO(Integer userId) {
        SocialUserViolationModel socialUserViolationModel = socialUserViolationApi.findOneByUserId(userId);
        return socialUserViolationModel;
    }

    /*public static UserDO get(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialNullUserException();
        }
        return UserUtils.get(Integer.valueOf(userId));
    }*/

    public static SocialuniUserModel getUserNotNull(Integer userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        SocialuniUserModel socialUserDO = getAllowNull(userId);
        if (socialUserDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserDO;
    }

    public static SocialuniUserModel getUserByUuid(String uid) {
        Integer id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uid);
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserModel getAllowNull(Integer userId) {
        if (userId == null) {
            return null;
        }
        return userApi.findOneByUnionId(userId);
    }

    public static Boolean getUserIsIdentityAuth(Integer userId) {
        SocialUserIdentityAuthDO socialUserIdentityAuthDO = socialUserIdentityAuthRepository.findFirstByUserId(userId);
        if (socialUserIdentityAuthDO == null) {
            return false;
        }
        return UserIdentityAuthStatus.authSuccessList.contains(socialUserIdentityAuthDO.getStatus());
    }

    public static SocialuniUserModel getByUid(String uid) {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUnionId(SocialuniAccountProviderType.socialuni, uid);
        if (socialUserAccountDO == null) {
            throw new SocialNullUserException();
        }
        return SocialuniUserUtil.getUserNotNull(socialUserAccountDO.getUserId());
    }

    public static boolean isMine(SocialuniUserModel mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }
}
