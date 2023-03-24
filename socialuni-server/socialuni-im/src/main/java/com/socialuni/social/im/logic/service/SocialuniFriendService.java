package com.socialuni.social.im.logic.service;


import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.dao.DO.SocialuniFriendDO;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.annotation.Resource;

@Resource
public class SocialuniFriendService {

    //添加朋友
    public void addFriend(Integer beUserId) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

//        userauti
        //判断是否已为好友
        SocialuniFriendDO socialuniFriendDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniFriendDO.class);
        if (socialuniFriendDO != null) {
            throw new SocialBusinessException("对方已经是您的好友了，禁止重复添加");
        }

        //对方删除和未响应的数据
        Long countNum = SocialuniUserContactRepositoryFacede.countByUserIdAndBeUserIdAndStatus(mineUserId, beUserId, SocialuniAddFriendStatus.init, SocialuniFriendApplyRecordDO.class);

        if (countNum > 2) {
            throw new SocialBusinessException("禁止频繁添加同一人好友");
        }

    }

    //删除朋友
    public void deleteFriend() {

    }
}
