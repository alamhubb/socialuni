package com.socialuni.center.web.utils;

import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.exception.SocialUserBannedException;
import com.socialuni.social.entity.model.DO.dev.ThirdUserDO;
import com.socialuni.social.entity.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.social.exception.SocialNullUserException;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CenterUserUtil {
    private static ThirdUserRepository thirdUserRepository;

    @Resource
    public void setThirdUserRepository(ThirdUserRepository thirdUserRepository) {
        CenterUserUtil.thirdUserRepository = thirdUserRepository;
    }

    public static Integer getMineUserIdAllowNull() {
        UserDO user = CenterUserUtil.getMineUserAllowNull();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public static Integer getMineUserId() {
        UserDO user = CenterUserUtil.getMineUser();
        return user.getId();
    }

    public static String getMineUserStringId() {
        Integer userId = CenterUserUtil.getMineUserId();
        if (userId == null) {
            throw new SocialNullUserException();
        }
        return userId.toString();
    }

    public static UserDO getMineUserAllowNull() {
        ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO();
        return getMineUser(tokenDO);
    }

    public static UserDO getMineUser() {
        UserDO user = CenterUserUtil.getMineUserAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }


    public static boolean isMine(Integer userId) {
        Integer mineUserId = CenterUserUtil.getMineUserId();
        return userId.equals(mineUserId);
    }

    public static UserDO getMineUser(String token) {
        ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO(token);
        return getMineUser(tokenDO);
    }

    public static Integer getMineUserIdInterceptor() {
        UserDO user = CenterUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getId();
    }

    public static UserDO getMineUserInterceptor() {
        ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO();
        if (tokenDO == null) {
            return null;
        }
        UserDO user = SocialUserUtil.get(tokenDO.getUserId());
        if (user.getStatus().equals(UserStatus.violation)) {
            return null;
        }
        //返回user
        return user;
    }

    private static UserDO getMineUser(ThirdUserTokenDO tokenDO) {
        if (tokenDO == null) {
            return null;
        }
        UserDO user = SocialUserUtil.get(tokenDO.getUserId());
        if (user.getStatus().equals(UserStatus.violation)) {
            throw new SocialUserBannedException(user);
        }
        //返回user
        return user;
    }

    public static ThirdUserDO getMineThirdUser(Integer mineUserId) {
        Integer devId = DevAccountUtils.getDevIdNotNull();
        ThirdUserDO thirdUserDO = thirdUserRepository.findByDevIdAndUserId(devId, mineUserId);
        return thirdUserDO;
    }

    public static String getMineThirdUserId() {
        UserDO mineUser = CenterUserUtil.getMineUser();
        return CenterUserUtil.getMineThirdUserId(mineUser.getId());
    }

    public static String getMineThirdUserId(Integer mineUserId) {
        ThirdUserDO thirdUserDO = CenterUserUtil.getMineThirdUser(mineUserId);
        if (thirdUserDO == null) {
            throw new SocialNullUserException();
        }
        return thirdUserDO.getThirdUserId();
    }


    public static String getMineUserPhoneNum() {
        UserDO userDO = CenterUserUtil.getMineUser();
        return SocialUserUtil.getUserPhoneNum(userDO.getId());
    }

    public static UserDO get(String userId) {
        Integer id = UnionIdDbUtil.getUserIdByUid(userId);
        return SocialUserUtil.get(id);
    }
}