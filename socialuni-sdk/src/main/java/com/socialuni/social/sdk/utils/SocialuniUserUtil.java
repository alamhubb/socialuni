package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.sdk.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.sdk.exception.exception.SocialNullUserException;
import com.socialuni.social.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.sdk.dao.DO.user.SocialTokenDO;
import com.socialuni.social.sdk.dao.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.sdk.dao.utils.SocialTokenDOUtil;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.api.SocialRequestUserConfig;
import com.socialuni.social.user.sdk.entity.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.entity.SocialUserViolationDo;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.SocialUserViolationRepository;
import com.socialuni.social.user.sdk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserUtil {
    private static UserRepository userApi;
    private static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserViolationRepository socialUserViolationApi;

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
    public void setCommonUserRepository(UserRepository userApi) {
        SocialuniUserUtil.userApi = userApi;
    }


    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniUserUtil.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    @Resource
    public void setSocialUserViolationRepository(SocialUserViolationRepository socialUserViolationApi) {
        SocialuniUserUtil.socialUserViolationApi = socialUserViolationApi;
    }


    public static Integer getMineUserIdAllowNull(SocialuniUserDo mineUser) {
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

    public static SocialuniUserDo getMineUserNotNull() {
        //解析token
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        //返回user
        return mineUser;
    }

    //下面都是联盟的
    public static SocialuniUserDo getMineUserNotNull(String token) {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialuniUserDo getMineUserAllowNull() {
        Integer userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(userId);
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    public static Integer getMineUserIdInterceptor() {
        SocialuniUserDo user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static String getMineUserIdStrInterceptor() {
        SocialuniUserDo user = SocialuniUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId().toString();
    }

    public static SocialuniUserDo getMineUserInterceptor() {
        SocialuniUserDo user = SocialuniUserUtil.getMineUserAllowNull();
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
    public static SocialuniUserDo getUserByToken(String token) {
        //解析token
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull(token);
        if (tokenDO == null) {
            return null;
        }
        //返回user
        SocialuniUserDo user = SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        SocialuniUserDo userDO = SocialuniUserUtil.getMineUserAllowNull();
        return SocialuniUserUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserUtil.getUserPhoneDO(userId);
        if (SocialUserPhoneDo == null) {
            return null;
        }
        return SocialUserPhoneDo.getPhoneNum();
    }

    public static SocialUserPhoneDo getUserPhoneNumDO(Integer userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserUtil.getUserPhoneDO(userId);
        if (SocialUserPhoneDo == null) {
            return null;
        }
        return SocialUserPhoneDo;
    }

    public static SocialUserPhoneDo getUserPhoneDO(Integer userId) {
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(userId);
        return SocialUserPhoneDo;
    }

    public static SocialUserViolationDo getUserViolationDO(Integer userId) {
        SocialUserViolationDo SocialUserViolationDo = socialUserViolationApi.findOneByUserId(userId);
        return SocialUserViolationDo;
    }

    /*public static UserDO get(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialNullUserException();
        }
        return UserUtils.get(Integer.valueOf(userId));
    }*/

    public static SocialuniUserDo getUserNotNull(Integer userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        SocialuniUserDo socialUserDO = getAllowNull(userId);
        if (socialUserDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserDO;
    }

    public static SocialuniUserDo getUserByUuid(String uid) {
        Integer id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uid);
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserDo getAllowNull(Integer userId) {
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

    public static boolean isMine(SocialuniUserDo mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }
}
