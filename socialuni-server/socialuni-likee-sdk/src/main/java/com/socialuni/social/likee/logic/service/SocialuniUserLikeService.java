package com.socialuni.social.likee.logic.service;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.ChatRO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.likee.config.SocialuniLikeAllConfig;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeChatDO;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeChatManage;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeManage;
import com.socialuni.social.likee.model.SocialuniLikeChatRO;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.constant.SocialuniOrderDetailType;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEntity;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class SocialuniUserLikeService {
    @Resource
    SocialuniUserLikeManage socialuniUserLikeManage;

    @Resource
    SocialuniUserLikeChatManage socialuniUserLikeChatManage;

    @Resource
    ChatService chatService;

    public ResultRO<SocialuniLikeChatRO> queryChat(SocialuniChatQueryQO socialuniChatQueryQO) {

        //为什么要查询，因为要加一个参数，发送消息时是否需要扣除金币
        ResultRO<ChatRO> chatROResultRO = chatService.queryChat(socialuniChatQueryQO);

        ChatRO chatRO = chatROResultRO.getData();

        SocialuniLikeChatRO socialuniLikeChatRO = BeanUtil.toBean(chatRO, SocialuniLikeChatRO.class);
        socialuniLikeChatRO.setPayCoinNum(0);
        socialuniLikeChatRO.setNeedPayOpen(true);

        //私聊
        String chatUuid = socialuniChatQueryQO.getChatId();

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer chatId = getChatId(mineUserId, chatUuid);

        //查询是否创建了
        SocialuniUserLikeChatDO socialuniUserLikeChatDO = socialuniUserLikeChatManage.get(chatId);

        if (socialuniUserLikeChatDO != null) {
            socialuniLikeChatRO.setNeedPayOpen(false);
            if (mineUserId.equals(socialuniUserLikeChatDO.getUserId())) {
                socialuniLikeChatRO.setPayCoinNum(SocialuniLikeAllConfig.getLikeAllConfigBO().getSendLikeMsgNeedPayCoinNum());
            }
        }

        return ResultRO.success(socialuniLikeChatRO);
    }


    @Resource
    private SocialuniMessageService messageService;
    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;


    @Transactional
    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        //msg支持自定义的展示类型
        ResultRO<SocialMessageRO> resultRO = messageService.sendMsg(msgAddVO);

        SocialMessageRO socialMessageRO = resultRO.getData();

        String msgId = socialMessageRO.getId();

        Integer msgIdd = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(msgId);


        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        String receiveIdUid = msgAddVO.getReceiveId();

        Integer chatId = getChatId(mineUserId, receiveIdUid);

        //查询是否创建了
        SocialuniUserLikeChatDO socialuniUserLikeChatDO = socialuniUserLikeChatManage.getOrCreate(chatId);

        if (mineUserId.equals(socialuniUserLikeChatDO.getUserId())) {
            socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(mineUserId, -SocialuniLikeAllConfig.getLikeAllConfigBO().getSendLikeMsgNeedPayCoinNum(), SocialuniCoinOrderType.consume, SocialuniOrderDetailType.msg, msgIdd);
        }
        return resultRO;
    }

    private Integer getChatId(Integer mineUserId, String chatId) {
        SocialuniUnionIdModler socialuniUnionIdModler = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatId);

        String contentType = socialuniUnionIdModler.getContentType();


        //私聊
        if (contentType.equals(SocialuniContentType.user)) {
            Integer beUserId = socialuniUnionIdModler.getId();
            SocialuniChatUserDO chatUserDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUserId, beUserId, SocialuniChatUserDO.class);

            return chatUserDO.getChatId();
        }
        return null;
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

        sendLikeUserMsg(addVO.getUserId());
        return socialuniUserLikeDO;
    }

    @Transactional
    public void sendLikeUserMsg(String receiveUserId) {
        MessageAddVO msgAddVO = new MessageAddVO();
        msgAddVO.setReceiveId(receiveUserId);
        //获取用户的币，获取发送需要的币
        msgAddVO.setContent("你好，在干嘛呢");
        sendMsg(msgAddVO);
    }
}
