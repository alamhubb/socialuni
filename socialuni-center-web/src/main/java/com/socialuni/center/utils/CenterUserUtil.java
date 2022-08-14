package com.socialuni.center.utils;

import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.exception.SocialUserBannedException;
import com.socialuni.sdk.model.DO.dev.DevAccountDO;
import com.socialuni.sdk.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.sdk.model.DO.user.SocialTokenDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.utils.*;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CenterUserUtil {
    //被封禁的用户，可以干嘛，不可以干嘛， 只能往日志里记录id时使用，
    //只有getMineUser时判断状态，只会限制自己
    //携带用户信息，
    //查询talks的时候可以提示，用户凭证过期，请重新登录
    //如果访问了需要用户标识的接口比如调用了post，需要提示，用户未登录，

    //第一层，是否被封禁， 二层用户凭证过期， 三层未登录。
    //手动获取时，判断是否未登录
    //拦截器里面，判断您是否被封禁

    // 获取可为空的时候，就有可能token过去了
    // 发送手机号时，是可以为空的，为空的要校验是否超时
    // 不允许为空的要判断是否未登录


    public static SocialUserDO getMineUserAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        SocialUserDO userDO;
        //区分本应用和其他应用的不同逻辑
        if (devAccountDO.getId() == 1) {
//            ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO();
            userDO = SocialUserUtil.getMineUserAllowNull();
        } else {
            userDO = CenterUserUtil.getDataUser();
        }
        return userDO;
    }


    public static SocialUserDO getDataUser() {
        String thirdUserId = CenterTokenUtil.getDataUserUnionId();
        if (StringUtils.isEmpty(thirdUserId)) {
            return null;
        }
        Integer userId = UnionIdDbUtil.getUserUnionIdByUidNotNull(thirdUserId);
        if (userId == null) {
            throw new SocialParamsException("错误的用户标识");
        }
        return SocialUserUtil.getUserNotNull(userId);
    }


    //改名为notnull就行
    public static SocialUserDO getMineUserNotNull() {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        if (user.getStatus().equals(UserStatus.violation)) {
            throw new SocialUserBannedException(user);
        }
        return user;
    }


    //下面都是联盟的

    public static SocialUserDO getMineUserNotNull(String token) {
        ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO(token);
        return getMineUserNotNull(tokenDO);
    }

    private static SocialUserDO getMineUserNotNull(ThirdUserTokenDO tokenDO) {
        if (tokenDO == null) {
            return null;
        }
        SocialUserDO user = SocialUserUtil.getUserNotNull(tokenDO.getUserId());
        if (user.getStatus().equals(UserStatus.violation)) {
            throw new SocialUserBannedException(user);
        }
        //返回user
        return user;
    }

    public static Integer getMineUserIdAllowNull() {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        if (user == null) {
            return null;
        }
        return user.getUnionId();
    }

    public static Integer getMineUserId() {
        SocialUserDO user = CenterUserUtil.getMineUserNotNull();
        return user.getUnionId();
    }

    public static String getMineUserUnionId() {
        SocialUserDO user = CenterUserUtil.getMineUserNotNull();
        return UnionIdDbUtil.getUidByUnionIdNotNull(user.getUnionId());
    }

    public static String getUserUnionId(SocialUserDO userDO) {
        return UnionIdDbUtil.getUidByUnionIdNotNull(userDO.getUnionId());
    }

    public static String getMineUserStringId() {
        Integer userId = CenterUserUtil.getMineUserId();
        if (userId == null) {
            throw new SocialNotLoginException();
        }
        return userId.toString();
    }


    public static boolean isMine(Integer userId) {
        Integer mineUserId = CenterUserUtil.getMineUserId();
        return userId.equals(mineUserId);
    }

    public static Integer getMineUserIdInterceptor() {
        SocialUserDO user = CenterUserUtil.getMineUserInterceptor();
        if (user == null) {
            return null;
        }
        //返回user
        return user.getUnionId();
    }

    public static SocialUserDO getMineUserInterceptor() {
        SocialTokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull();
        if (tokenDO == null) {
            return null;
        }
        SocialUserDO user = SocialUserUtil.getUserNotNull(tokenDO.getUserId());
        if (user.getStatus().equals(UserStatus.violation)) {
            return null;
        }
        //返回user
        return user;
    }

    //只要getUserNotNull的时候会报错。


    /*public static ThirdUserDO getMineThirdUser(Integer mineUserId) {
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
    }*/


    public static String getMineUserPhoneNum() {
        SocialUserDO userDO = CenterUserUtil.getMineUserNotNull();
        return SocialUserUtil.getUserPhoneNum(userDO.getUnionId());
    }

    public static SocialUserDO get(String userId) {
        Integer id = UnionIdDbUtil.getUserUnionIdByUidNotNull(userId);
        return SocialUserUtil.getUserNotNull(id);
    }
}