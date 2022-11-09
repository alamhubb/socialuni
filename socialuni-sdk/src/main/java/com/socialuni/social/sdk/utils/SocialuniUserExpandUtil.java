package com.socialuni.social.sdk.utils;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.sdk.constant.socialuni.UserIdentityAuthStatus;
import com.socialuni.social.sdk.dao.DO.user.SocialUserIdentityAuthDO;
import com.socialuni.social.sdk.dao.repository.user.identity.SocialUserIdentityAuthRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.api.SocialRequestUserConfig;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.model.DO.SocialTokenDO;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.model.DO.SocialUserViolationDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.repository.SocialUserViolationRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.SocialTokenDOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialuniUserExpandUtil {
    private static SocialuniUserRepository userApi;
    private static SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserViolationRepository socialUserViolationApi;

    private static SocialRequestUserConfig socialRequestUserConfig;

    @Resource
    public void setSocialRequestUserConfig(SocialRequestUserConfig socialRequestUserConfig) {
        SocialuniUserExpandUtil.socialRequestUserConfig = socialRequestUserConfig;
    }

    @Resource
    public void setSocialUserIdentityAuthRepository(SocialUserIdentityAuthRepository socialUserIdentityAuthRepository) {
        SocialuniUserExpandUtil.socialUserIdentityAuthRepository = socialUserIdentityAuthRepository;
    }

    @Resource
    public void setCommonUserRepository(SocialuniUserRepository userApi) {
        SocialuniUserExpandUtil.userApi = userApi;
    }


    @Resource
    public void setSocialUserPhoneStore(SocialUserPhoneRedis socialUserPhoneRedis) {
        SocialuniUserExpandUtil.socialUserPhoneRedis = socialUserPhoneRedis;
    }

    @Resource
    public void setSocialUserViolationRepository(SocialUserViolationRepository socialUserViolationApi) {
        SocialuniUserExpandUtil.socialUserViolationApi = socialUserViolationApi;
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
        SocialuniUserDo mineUser = SocialuniUserExpandUtil.getMineUserAllowNull();
        if (mineUser == null) {
            throw new SocialNotLoginException();
        }
        //返回user
        return mineUser;
    }

    //下面都是联盟的
    public static SocialuniUserDo getMineUserNotNull(String token) {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialuniUserExpandUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialuniUserDo getMineUserAllowNull() {
        Integer userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialuniUserDo mineUser = SocialuniUserExpandUtil.getUserNotNull(userId);
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    public static Integer getMineUserIdInterceptor() {
        SocialuniUserDo user = SocialuniUserExpandUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static String getMineUserIdStrInterceptor() {
        SocialuniUserDo user = SocialuniUserExpandUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId().toString();
    }

    public static SocialuniUserDo getMineUserInterceptor() {
        SocialuniUserDo user = SocialuniUserExpandUtil.getMineUserAllowNull();
        if (user == null) {
            return null;
        }
        if (user.getStatus().equals(SocialuniUserStatus.violation)) {
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
        SocialuniUserDo user = SocialuniUserExpandUtil.getUserNotNull(tokenDO.getUserId());
        return user;
    }

    public static String getMineUserPhoneNum() {
        SocialuniUserDo userDO = SocialuniUserExpandUtil.getMineUserAllowNull();
        return SocialuniUserExpandUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static String getUserPhoneNum(Integer userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserExpandUtil.getUserPhoneDO(userId);
        if (SocialUserPhoneDo == null) {
            return null;
        }
        return SocialUserPhoneDo.getPhoneNum();
    }

    public static SocialUserPhoneDo getUserPhoneNumDO(Integer userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserExpandUtil.getUserPhoneDO(userId);
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
        return SocialuniUserExpandUtil.getUserNotNull(id);
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
