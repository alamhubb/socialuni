package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.content.constant.SocialuniUserStatus;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.config.SocialuniUserSysConfig;
import com.socialuni.social.user.sdk.exception.SocialUserBannedException;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.dao.DO.SocialUserViolationDo;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.dao.repository.SocialUserViolationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialuniUserUtil {
    private static SocialuniUserRepository userApi;
    private static SocialUserPhoneRedis socialUserPhoneRedis;
    private static SocialUserViolationRepository socialUserViolationApi;

    private static SocialRequestUserConfig socialRequestUserConfig;

    @Resource
    public void setSocialRequestUserConfig(SocialRequestUserConfig socialRequestUserConfig) {
        SocialuniUserUtil.socialRequestUserConfig = socialRequestUserConfig;
    }

    @Resource
    public void setCommonUserRepository(SocialuniUserRepository userApi) {
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


    public static Long getMineUserIdAllowNull(SocialuniUserDo mineUser) {
        //解析token
        if (mineUser == null) {
            return null;
        }
        Long userId = mineUser.getUnionId();
        return userId;
    }

    public static Long getMineUserIdNotNull() {
        return getMineUserNotNull().getUnionId();
    }

    public static boolean hasMineUser() {
        return getMineUserAllowNull() != null;
    }

    public static Long getMineUserUuidIdNotNull() {
        Long mineUserId = getMineUserIdNotNull();
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
        SocialuniTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDONotNull(token);
        return SocialuniUserUtil.getUserNotNull(tokenDO.getUserId());
    }

    public static SocialuniUserDo getMineUserAllowNull() {
        Long userId = getMineUserIdAllowNull();
        if (userId == null) {
            return null;
        }
        //返回user
        SocialuniUserDo mineUser = SocialuniUserUtil.getUserNotNull(userId);
        if (!SocialuniUserSysConfig.bandAllowLogin) {
            //如果手机号违规，则返回手机号不可用
            if (mineUser.getStatus().equals(SocialuniUserStatus.violation)) {
                throw new SocialUserBannedException();
            }
        }
        return mineUser;
    }

    public static Long getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
    }

    //必须有，websocket无法从request中获取token只能传入
    public static SocialuniUserDo getUserByToken(String token) {
        //解析token
        SocialuniTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull(token);
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

    public static String getUserPhoneNum(Long userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserUtil.getUserPhoneDO(userId);
        if (SocialUserPhoneDo == null) {
            return null;
        }
        return SocialUserPhoneDo.getPhoneNum();
    }

    public static SocialUserPhoneDo getUserPhoneNumDO(Long userId) {
        SocialUserPhoneDo SocialUserPhoneDo = SocialuniUserUtil.getUserPhoneDO(userId);
        if (SocialUserPhoneDo == null) {
            return null;
        }
        return SocialUserPhoneDo;
    }

    public static SocialUserPhoneDo getUserPhoneDO(Long userId) {
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(userId);
        return SocialUserPhoneDo;
    }

    public static SocialuniUserDo getUserByPhoneNumNotNull(String phoneNum) {
        SocialUserPhoneDo socialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
        if (socialUserPhoneDo == null) {
            throw new SocialParamsException("手机号异常");
        }
        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(socialUserPhoneDo.getUserId());
        return socialuniUserDo;
    }

    public static SocialUserViolationDo getUserViolationDO(Long userId) {
        SocialUserViolationDo SocialUserViolationDo = socialUserViolationApi.findOneByUserId(userId);
        return SocialUserViolationDo;
    }

    /*public static UserDO get(Long userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new SocialNullUserException();
        }
        return UserUtils.get(Integer.valueOf(userId));
    }*/


    public static List<SocialuniUserDo> getUsers(List<Long> ids) {
        List<SocialuniUserDo> userDos = new ArrayList<>();
        for (Long id : ids) {
            SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(id);
            userDos.add(socialuniUserDo);
        }
        return userDos;
    }

    public static SocialuniUserDo getUserByUuid(String uid) {
        Long id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uid);
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserDo getUserByUuid(Long uid) {
        Long id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uid);
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserDo getUserByUuidAllowNull(String uid) {
        Long id = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(uid);
        if (id == null) {
            return null;
        }
        return SocialuniUserUtil.getUserNotNull(id);
    }

    public static SocialuniUserDo getAllowNull(Long userId) {
        if (userId == null) {
            return null;
        }
        return userApi.findOneByUnionId(userId);
    }

    public static SocialuniUserDo getUserNotNull(Long userId) {
        if (userId == null) {
            throw new SocialNullUserException();
        }
        SocialuniUserDo socialUserDO = getAllowNull(userId);
        if (socialUserDO == null) {
            throw new SocialNullUserException();
        }
        return socialUserDO;
    }
//
//    public static SocialuniUserDo getNotNull(Long userId) {
//        if (userId == null) {
//            throw new SocialParamsException("用户id异常");
//        }
//        SocialuniUserDo socialuniUserDo = userApi.findOneByUnionId(userId);
//        if (socialuniUserDo == null) {
//            throw new SocialParamsException("用户id异常");
//        }
//        return socialuniUserDo;
//    }

    public static boolean isMine(SocialuniUserDo mineUser, Long userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }

    public static SocialuniUserDo getSystemUserNotNull() {
        String phoneNum = DevAccountFacade.getDevPhoneNumNotNull();

        SocialuniUserDo systemUser = getUserByPhoneNumNotNull(phoneNum);

        return systemUser;
    }

    public static Long getSystemUserIdNotNull() {
        return getSystemUserNotNull().getUserId();
    }
}
