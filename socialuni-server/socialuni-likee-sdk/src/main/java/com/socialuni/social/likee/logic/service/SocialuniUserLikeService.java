package com.socialuni.social.likee.logic.service;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.common.sdk.utils.ErrorLogUtil;
import com.socialuni.social.im.api.model.QO.SocialuniChatQueryQO;
import com.socialuni.social.im.api.model.QO.chat.ChatReadVO;
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
import com.socialuni.social.sdk.im.logic.foctory.SocialChatROFactory;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SocialuniUserLikeService {
    @Resource
    SocialuniUserLikeManage socialuniUserLikeManage;

    @Resource
    SocialuniUserLikeChatManage socialuniUserLikeChatManage;

    @Resource
    ChatService chatService;

    public ResultRO<List<SocialuniLikeChatRO>> queryChatList() {
        ResultRO<List<ChatRO>> resultRO = chatService.queryChatList();
        List<ChatRO> data = resultRO.getData();

        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        List<SocialuniLikeChatRO> socialuniLikeChatROList = data.stream().map(item -> getSocialuniLikeChatRO(item, mineUserId)).collect(Collectors.toList());

        return ResultRO.success(socialuniLikeChatROList);
    }

    public ResultRO<SocialuniLikeChatRO> queryChat(SocialuniChatQueryQO socialuniChatQueryQO) {

        //为什么要查询，因为要加一个参数，发送消息时是否需要扣除金币
        ResultRO<ChatRO> chatROResultRO = chatService.queryChat(socialuniChatQueryQO);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniLikeChatRO socialuniLikeChatRO = getSocialuniLikeChatRO(chatROResultRO.getData(), mineUserId);

        return ResultRO.success(socialuniLikeChatRO);
    }

    private SocialuniLikeChatRO getSocialuniLikeChatRO(ChatRO chatRO, Integer mineUserId) {

        SocialuniLikeChatRO socialuniLikeChatRO = BeanUtil.toBean(chatRO, SocialuniLikeChatRO.class);
        socialuniLikeChatRO.setSendMsgNeedCoin(0);
        socialuniLikeChatRO.setNeedPayOpen(true);

        //私聊
        String chatUuid = chatRO.getId();

        SocialuniChatUserDO socialuniChatUserDO = SocialChatROFactory.getSingleChatUser(chatUuid);

        //群聊
        if (socialuniChatUserDO == null) {
            socialuniLikeChatRO.setNeedPayOpen(false);
            return socialuniLikeChatRO;
        }

        //查询是否创建了
        SocialuniUserLikeChatDO socialuniUserLikeChatDO = socialuniUserLikeChatManage.get(socialuniChatUserDO.getChatId());
        if (socialuniUserLikeChatDO != null) {
            socialuniLikeChatRO.setNeedPayOpen(false);

            if (mineUserId != null) {
                if (mineUserId.equals(socialuniUserLikeChatDO.getUserId())) {
                    socialuniLikeChatRO.setSendMsgNeedCoin(SocialuniLikeAllConfig.getLikeAllConfigBO().getSendLikeMsgNeedPayCoinNum());
                }
            }

        }
        return socialuniLikeChatRO;
    }


    @Resource
    private SocialuniMessageService messageService;
    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;


    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        //msg支持自定义的展示类型
        ResultRO<SocialMessageRO> resultRO = messageService.sendMsg(msgAddVO);

        System.out.println("fanhuile");
        //        return resultRO;
        return ResultRO.success();

//        SocialMessageRO socialMessageRO = resultRO.getData();
//
//        String msgId = socialMessageRO.getId();
//
//        Integer msgIdd = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(msgId);
//
//
//        String receiveIdUid = msgAddVO.getReceiveId();
//
//        SocialuniChatUserDO socialuniChatUserDO = SocialChatROFactory.getSingleChatUser(receiveIdUid);
//        if (socialuniChatUserDO == null) {
//            return resultRO;
//        }
//        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
//        //查询是否创建了
//        SocialuniUserLikeChatDO socialuniUserLikeChatDO = socialuniUserLikeChatManage.getOrCreate(socialuniChatUserDO.getChatId());
//
//        if (mineUserId.equals(socialuniUserLikeChatDO.getUserId())) {
//            socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(mineUserId, -SocialuniLikeAllConfig.getLikeAllConfigBO().getSendLikeMsgNeedPayCoinNum(), SocialuniCoinOrderType.consume, SocialuniOrderDetailType.msg, msgIdd);
//        }
//        return resultRO;
    }

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

    public void sendLikeUserMsg(String receiveUserId) {
        MessageAddVO msgAddVO = new MessageAddVO();
        msgAddVO.setReceiveId(receiveUserId);
        //获取用户的币，获取发送需要的币
        msgAddVO.setContent("你好，在干嘛呢");
        sendMsg(msgAddVO);
    }
}
