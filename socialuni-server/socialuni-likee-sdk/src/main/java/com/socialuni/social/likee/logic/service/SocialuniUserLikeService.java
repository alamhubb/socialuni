package com.socialuni.social.likee.logic.service;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.likee.config.SocialuniLikeAllConfig;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeManageHa;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.constant.SocialuniOrderDetailType;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEntity;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class SocialuniUserLikeService {
    @Resource
    SocialuniUserLikeManageHa socialuniUserLikeManage;

    @Resource
    private SocialuniMessageService messageService;
    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;


    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        //msg支持自定义的展示类型
        ResultRO<SocialMessageRO> resultRO = messageService.sendMsg(msgAddVO);

        SocialMessageRO socialMessageRO = resultRO.getData();

        String msgId = socialMessageRO.getId();

        Integer msgIdd = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(msgId);

        socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(mineUserId, SocialuniLikeAllConfig.getLikeAllConfigBO().getSendLikeMsgNeedPayCoinNum(), SocialuniCoinOrderType.consume, SocialuniOrderDetailType.msg, msgIdd);

        return resultRO;
    }

    @Transactional
    public SocialuniUserLikeDO likeUser(SocialuniUserIdQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getUserId());
        if (Objects.equals(beUserId, mineUserId)) {
            throw new SocialParamsException("不能喜欢自己哦");
        }
        SocialuniUserLikeDO socialuniUserLikeDO = socialuniUserLikeManage.createOrUpdateLikeStatus(mineUserId, beUserId);

        sendLikeUserMsg(mineUserId, addVO.getUserId());
        return socialuniUserLikeDO;
    }

    public void sendLikeUserMsg(Integer mineUserId, String receiveUserId) {
        MessageAddVO msgAddVO = new MessageAddVO();
        msgAddVO.setReceiveId(receiveUserId);
        //获取用户的币，获取发送需要的币
        msgAddVO.setContent("你好，在干嘛呢");
        sendMsg(msgAddVO);
    }
}
