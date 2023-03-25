package com.socialuni.social.im.logic.service;


import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import javax.annotation.Resource;

@Resource
public class SocialuniFriendService {

    //添加朋友
    public void addFriend(Integer beUserId) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        //添加对方的时候只看对方的状态，不看自己的状态

        //判断是否已为好友
        SocialuniFriendApplyRecordDO socialuniFriendDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(mineUserId, beUserId, SocialuniAddFriendStatus.enable, SocialuniFriendApplyRecordDO.class);
        if (socialuniFriendDO != null) {
            throw new SocialBusinessException("对方已经是您的好友了，禁止重复添加");
        }

        //对方删除和未响应的数据
        Long countNum = SocialuniUserContactRepositoryFacede.countByUserIdAndBeUserIdAndStatus(mineUserId, beUserId, SocialuniAddFriendStatus.init, SocialuniFriendApplyRecordDO.class);

        if (countNum > 2) {
            throw new SocialBusinessException("禁止频繁添加同一人好友");
        }


        //首先生成一条记录
        SocialuniFriendApplyRecordDO socialuniFriendApplyRecordDO = new SocialuniFriendApplyRecordDO(mineUserId, beUserId);

        socialuniFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.save(socialuniFriendApplyRecordDO);


        //如果对方有初始，加过你，或者正常，你把对方删了的情况，则成功为好友
        //判断对方是否已添加过你，忽略也为初始，没有拒绝状态
        Long beUserAddUserCount = SocialuniUserContactRepositoryFacede.countByUserIdAndBeUserIdAndNotStatus(beUserId, mineUserId, SocialuniAddFriendStatus.delete, SocialuniFriendApplyRecordDO.class);

        //删除好友只会把自己这边的状态改为删除
        if (beUserAddUserCount > 0) {
            socialuniFriendApplyRecordDO.setStatus(SocialuniAddFriendStatus.enable);


        }
    }

    //删除朋友
    public void deleteFriend() {

    }
}
