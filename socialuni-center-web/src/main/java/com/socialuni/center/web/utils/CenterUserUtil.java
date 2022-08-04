package com.socialuni.center.web.utils;

import com.socialuni.center.web.repository.UniUserAccountRepository;
import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.exception.SocialNotLoginException;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.exception.SocialUserBannedException;
import com.socialuni.social.entity.model.DO.dev.ThirdUserDO;
import com.socialuni.social.entity.model.DO.dev.ThirdUserTokenDO;
import com.socialuni.social.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.social.exception.SocialNullUserException;
import com.socialuni.social.sdk.constant.status.UserStatus;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.utils.SocialTokenDOUtil;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CenterUserUtil {
//    private static ThirdUserRepository thirdUserRepository;
    private static UniUserAccountRepository uniUserAccountRepository;

/*
    @Resource
    public void setThirdUserRepository(ThirdUserRepository thirdUserRepository) {
        CenterUserUtil.thirdUserRepository = thirdUserRepository;
    }
*/
    @Resource
    public void setUniUserAccountRepository(UniUserAccountRepository uniUserAccountRepository) {
        CenterUserUtil.uniUserAccountRepository = uniUserAccountRepository;
    }

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


    public static UserDO getMineUserAllowNull() {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        UserDO userDO;
        //区分本应用和其他应用的不同逻辑
        if (devAccountDO.getId() == 1) {
//            ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO();
            userDO = SocialUserUtil.getAllowNull(SocialUserUtil.getMineUserIdAllowNull());
        } else {
            String thirdUserId = CenterTokenUtil.getThirdUserId();

            UniUserAccountDO uniUserAccountDO = uniUserAccountRepository.findByDevIdAndThirdUserId(DevAccountUtils.getDevIdNotNull(), thirdUserId);
            if (uniUserAccountDO == null) {
                return null;
            }
            userDO = SocialUserUtil.get(uniUserAccountDO.getUserId());
        }
        return userDO;
    }

    //改名为notnull就行
    public static UserDO getMineUser() {
        UserDO user = CenterUserUtil.getMineUserAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        if (user.getStatus().equals(UserStatus.violation)) {
            throw new SocialUserBannedException(user);
        }
        return user;
    }



    //下面都是联盟的

    public static UserDO getMineUser(String token) {
        ThirdUserTokenDO tokenDO = CenterTokenUtil.getThirdUserTokenDO(token);
        return getMineUser(tokenDO);
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
            throw new SocialNotLoginException();
        }
        return userId.toString();
    }


    public static boolean isMine(Integer userId) {
        Integer mineUserId = CenterUserUtil.getMineUserId();
        return userId.equals(mineUserId);
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
        TokenDO tokenDO = SocialTokenDOUtil.getCommonTokenDOAllowNull();
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
        UserDO userDO = CenterUserUtil.getMineUser();
        return SocialUserUtil.getUserPhoneNum(userDO.getId());
    }

    public static UserDO get(String userId) {
        Integer id = UnionIdDbUtil.getUserIdByUid(userId);
        return SocialUserUtil.get(id);
    }
}