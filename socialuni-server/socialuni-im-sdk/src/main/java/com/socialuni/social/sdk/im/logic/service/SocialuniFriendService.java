package com.socialuni.social.sdk.im.logic.service;


import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniFriendApplyRecordDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniFriendApplyRecordRepository;
import com.socialuni.social.sdk.im.enumeration.SocialuniAddFriendStatus;
import com.socialuni.social.sdk.im.enumeration.SocialuniAddFriendType;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniChatUserDOFactory;
import com.socialuni.social.sdk.im.logic.foctory.SocialuniFriendApplyUserROFactory;
import com.socialuni.social.im.api.model.QO.friend.SocialuniFriendAddQO;
import com.socialuni.social.im.api.model.RO.SocialuniFriendApplyUserRO;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialuniFriendService {
    @Resource
    SocialuniFriendApplyRecordRepository socialuniFriendApplyRecordRepository;

    //添加朋友
    @Transactional
    public ResultRO<Void> addFriend(SocialuniFriendAddQO friendAddQO) {
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();

        SocialuniTextContentUtil.checkTextHasViolateWords(friendAddQO.getApplyMsg());

        if (!SocialuniAddFriendType.values.contains(friendAddQO.getReqType())) {
            throw new SocialBusinessException("错误的申请类型");
        }

        Long beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(friendAddQO.getUserId());
        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        //添加对方的时候只看对方的状态，不看自己的状态

        //判断是否已为好友, 看你是不是对方的好有
        SocialuniFriendApplyRecordDO socialuniFriendDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndStatus(beUserId, mineUserId, SocialuniAddFriendStatus.enable, SocialuniFriendApplyRecordDO.class);
        if (socialuniFriendDO != null) {
            throw new SocialBusinessException("对方已经是您的好友了，禁止重复添加");
        }

        //对方未响应的数据
        Long countNum = SocialuniUserContactRepositoryFacede.countByUserIdAndBeUserIdAndStatus(mineUserId, beUserId, SocialuniCommonStatus.enable, SocialuniFriendApplyRecordDO.class);

        //禁止多次添加，如果2次以上对上不加不能再申请
        if (countNum > 2) {
            throw new SocialBusinessException("禁止频繁添加同一人好友");
        }


        //首先生成一条记录
        SocialuniFriendApplyRecordDO socialuniFriendApplyRecordDO = new SocialuniFriendApplyRecordDO(mineUserId, beUserId, friendAddQO.getApplyMsg(), friendAddQO.getReqType());

        //发起申请成功
        socialuniFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.save(socialuniFriendApplyRecordDO);


        //如果对方有初始，加过你，或者正常，你把对方删了的情况，则成功为好友
        //有对方不为把你删除的记录
        //todo 这里消息需要验证，禁止发送敏感内容， 用户意思违规情况禁止添加好友


        SocialuniFriendApplyRecordDO beUserFriendApplyRecordDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserIdAndNotStatus(beUserId, mineUserId, SocialuniAddFriendStatus.delete, SocialuniFriendApplyRecordDO.class);

        if (beUserFriendApplyRecordDO == null) {
            return ResultRO.success();
        }
        //添加好友成功
        //删除好友只会把自己这边的状态改为删除
        socialuniFriendApplyRecordDO.setStatus(SocialuniAddFriendStatus.enable);
        socialuniFriendApplyRecordDO.setUpdateTime(new Date());
        SocialuniUserContactRepositoryFacede.save(socialuniFriendApplyRecordDO);

        beUserFriendApplyRecordDO.setStatus(SocialuniAddFriendStatus.enable);
        beUserFriendApplyRecordDO.setUpdateTime(new Date());
        SocialuniUserContactRepositoryFacede.save(beUserFriendApplyRecordDO);


        //加好友那边，则看的是有没有初始的，为初始以后就失效了。

        //看两个表的状态，先看好友表，有数据，如果对方为好友，。

        //好友状态，chat状态 ，维护chatUser状态。发送消息的时候只需要看chatUser状态就行。
        //给用户发消息。 只需要对方用户的id，就行。
        //那就是判断，读取和对方的chat，有的话展示，没有的话就不展示

        List<SocialuniChatUserDO> chatSocialuniUserDoS = SocialuniChatUserDOFactory.getOrCreateChatUsersBySingleSendMsg(mineUserId, beUserId);

        return ResultRO.success();
    }

    //删除朋友
    public ResultRO<List<SocialuniFriendApplyUserRO>> queryFriendApplyList() {
        //查询你申请的和被申请的。

        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        List<SocialuniFriendApplyRecordDO> list = socialuniFriendApplyRecordRepository.findTop30ByUserIdOrBeUserIdAndType(mineUserId, SocialuniAddFriendType.apply);

        List<SocialuniFriendApplyUserRO> list1 = list.stream().map(SocialuniFriendApplyUserROFactory::getSocialuniFriendApplyUserRO).collect(Collectors.toList());
        return ResultRO.success(list1);
    }
}
