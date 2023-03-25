package com.socialuni.social.im.logic.service;


import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

import javax.annotation.Resource;
import java.util.Date;

@Resource
public class SocialuniFriendService {

    //添加朋友
    public void addFriend(String userId) {
        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(userId);
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        //添加对方的时候只看对方的状态，不看自己的状态

        //判断是否已为好友, 看你是不是对方的好有
        SocialuniFriendApplyRecordDO socialuniFriendDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(beUserId, mineUserId, SocialuniAddFriendStatus.enable, SocialuniFriendApplyRecordDO.class);
        if (socialuniFriendDO != null) {
            throw new SocialBusinessException("对方已经是您的好友了，禁止重复添加");
        }

        //对方未响应的数据
        Long countNum = SocialuniUserContactRepositoryFacede.countByUserIdAndBeUserIdAndStatus(mineUserId, beUserId, SocialuniCommonStatus.init, SocialuniFriendApplyRecordDO.class);

        //禁止多次添加，如果2次以上对上不加不能再申请
        if (countNum > 2) {
            throw new SocialBusinessException("禁止频繁添加同一人好友");
        }


        //首先生成一条记录
        SocialuniFriendApplyRecordDO socialuniFriendApplyRecordDO = new SocialuniFriendApplyRecordDO(mineUserId, beUserId);

        //发起申请成功
        socialuniFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.save(socialuniFriendApplyRecordDO);


        //如果对方有初始，加过你，或者正常，你把对方删了的情况，则成功为好友
        //有对方不为把你删除的记录
        SocialuniFriendApplyRecordDO beUserFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndNotStatus(beUserId, mineUserId, SocialuniAddFriendStatus.delete, SocialuniFriendApplyRecordDO.class);

        if (beUserFriendApplyRecordDO == null) {
            return;
        }
        //添加好友成功
        //删除好友只会把自己这边的状态改为删除
        socialuniFriendApplyRecordDO.setStatus(SocialuniAddFriendStatus.enable);
        socialuniFriendApplyRecordDO.setUpdateTime(new Date());
        SocialuniUserContactRepositoryFacede.save(socialuniFriendApplyRecordDO);

        beUserFriendApplyRecordDO.setStatus(SocialuniAddFriendStatus.enable);
        beUserFriendApplyRecordDO.setUpdateTime(new Date());
        SocialuniUserContactRepositoryFacede.save(beUserFriendApplyRecordDO);

    }

    //删除朋友
    public void deleteFriend() {

    }
}
