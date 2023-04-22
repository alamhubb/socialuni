package com.socialuni.social.user.sdk.model.factory;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.api.model.user.SocialuniMineUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.model.DO.SocialUserPhoneDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.logic.redis.SocialUserPhoneRedis;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;

public class SocialuniMineUserROFactory {
    public final static SocialUserPhoneRedis socialUserPhoneRedis = SpringUtil.getBean(SocialUserPhoneRedis.class);

    public static SocialuniMineUserRO getMineUser() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return SocialuniMineUserROFactory.getMineUser(mineUser);
    }

    public static SocialuniMineUserRO getMineUser(SocialuniUserDo mineUser) {
        //用户关注粉丝数
        SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findUserPhoneByUserId(mineUser.getUnionId());

        return SocialuniMineUserROFactory.getMineUser(mineUser, SocialUserPhoneDo);
    }


    public static SocialuniMineUserRO getMineUser(SocialuniUserDo mineUser, SocialUserPhoneDo SocialUserPhoneDo) {
        SocialuniUserRO socialuniUserRO = SocialuniUserROFactory.getUserRO(mineUser, mineUser);
        SocialuniMineUserRO socialuniMineUserRO = new SocialuniMineUserRO(socialuniUserRO);
        //user详情信息
        if (SocialUserPhoneDo != null) {
            //只有自己的开发者才显示手机号
            if (DevAccountFacade.getDevIdNotNull().equals(SocialUserPhoneDo.getDevId())) {
                String realPhoneNum = SocialUserPhoneDo.getPhoneNum();
                if (StringUtils.isNotEmpty(realPhoneNum)) {
                    realPhoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
                    socialuniMineUserRO.setPhoneNum(realPhoneNum);
                }
            }
        }


        //注释掉圈子功能, 加入圈子
        /*Optional<ChatDO> optionalChatDO = chatRepository.findFirstOneByTypeAndStatusOrderByCreateTime(ChatType.system_group, CommonStatus.normal);
        if (optionalChatDO.isPresent()) {
            ChatDO chat = optionalChatDO.get();
            ChatUserDO chatUserDO = new ChatUserDO(chat.getId(), user.getId(), ChatType.system_group);
            chatUserRepository.save(chatUserDO);
        }

        Optional<UserDO> systemUserOptional = userRepository.findById(UserUtils.getSystemId());

        if (systemUserOptional.isPresent()) {
            UserDO sysUser = systemUserOptional.get();
            //所有人注册默认关注系统用户
            followService.addFlow(user, sysUser);
        }*/


        return socialuniMineUserRO;
    }

}
