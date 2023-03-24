package com.socialuni.social.user.base.facade;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialNullUserException;
import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import com.socialuni.social.user.base.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.base.dao.repository.SocialuniUserRepository;
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
        if (mineUser.getStatus().equals(SocialuniUserStatus.violation)) {
            throw new SocialUserBannedException();
        }
        return mineUser;
    }

    public static Integer getMineUserIdAllowNull() {
        //解析token
        return socialRequestUserConfig.getUserId();
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

    public static List<SocialuniUserDo> getUsers(List<Integer> ids) {
        List<SocialuniUserDo> userDos = new ArrayList<>();
        for (Integer id : ids) {
            SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserNotNull(id);
            userDos.add(socialuniUserDo);
        }
        return userDos;
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

    public static boolean isMine(SocialuniUserDo mineUser, Integer userId) {
        if (mineUser == null) {
            return false;
        }
        return userId.equals(mineUser.getUnionId());
    }
}
