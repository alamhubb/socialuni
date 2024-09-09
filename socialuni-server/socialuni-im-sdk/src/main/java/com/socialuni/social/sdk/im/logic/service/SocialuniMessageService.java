package com.socialuni.social.sdk.im.logic.service;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.im.api.feign.SocialuniMessageAPI;
import com.socialuni.social.im.api.model.QO.MessageQueryVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.sdk.im.enumeration.*;
import com.socialuni.social.sdk.im.logic.domain.SocialuniMsgDomain;
import com.socialuni.social.sdk.im.logic.entity.SocialuniMessageEntity;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.sdk.im.logic.foctory.SocialMessageROFactory;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatUserManage;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-06-16 12:39
 */
@Service
public class SocialuniMessageService {
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private SocialuniMessageRepository messageRepository;
    @Resource
    private SocialuniMessageEntity messageEntity;
    @Resource
    private SocialuniMessageReceiveRepository messageReceiveRepository;
    @Resource
    SocialuniChatRepository socialuniChatRepository;

    @Resource
    SocialuniChatUserManage socialuniChatUserManage;
    @Resource
    ChatService chatService;

    @Resource
    SocialuniMessageAPI socialuniMessageAPI;
    @Resource
    SocialuniMsgDomain socialuniMsgDomain;


    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {

        SocialMessageRO messageRO = socialuniMsgDomain.sendMsg(msgAddVO);

        String uuid = messageRO.getId();

        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<SocialMessageRO> socialMessageROResultRO = socialuniMessageAPI.sendMsg(msgAddVO);
            SocialMessageRO messageRO1 = socialMessageROResultRO.getData();
            SocialuniUnionIdFacede.updateUuidByUuidNotNull(uuid, messageRO1.getId());
            return socialMessageROResultRO;
        }
        return ResultRO.success(messageRO);
    }

   /* public ResultRO<SocialMessageRO> sendMsg(MessageAddVO msgAddVO) {
        Long chatId = SocialuniChatDOUtil.getChatId(msgAddVO.getChatId());

        String msgContent = msgAddVO.getContent();
        String msgType = msgAddVO.getType();
        if (StringUtils.isEmpty(msgType)) {
            msgType = MessageContentType.text;
        }
        if (StringUtils.isEmpty(msgContent)) {
            throw new SocialBusinessException("不能发布空内容");
        }
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();

        //校验内容是否违规
        SocialuniTextContentUtil.checkTextHasViolateWords(msgContent);





        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        Long unionId = null;

        if (socialuniUnionIdModler == null) {
            SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

            SocialuniChatUserDO socialuniChatUserDO = chatService.getSocialuniChatUserDO(chatId);

            if (UserType.system.equals(mineUser.getType()) || mineUser.getUserId().equals(socialuniChatUserDO.getUserId())) {

                SocialuniUserDo sendUser = mineUser;
                if (UserType.system.equals(mineUser.getType())) {
                    sendUser = SocialuniUserUtil.getUserNotNull(socialuniChatUserDO.getUserId());
                }
                SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(sendUser, socialuniChatUserDO.getBeUserId(), msgAddVO.getContent(), msgType);

                return ResultRO.success(socialMessageRO);
            }

            throw new SocialParamsException("参数异常3260");


            //则为chatUserId
        } else {
            unionId = socialuniUnionIdModler.getId();

            String idContentType = socialuniUnionIdModler.getContentType();
            //旧版本
            if (idContentType.equals(SocialuniContentType.user)) {
                //为私聊相关校验
                //后端区分这个值是群聊还是私聊。

                SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

                SocialMessageRO socialMessageRO = messageEntity.sendSingleMsg(mineUser, unionId, msgAddVO.getContent(), msgType);

//                ErrorLogUtil.error("不应该存在用户id的会话消息");

                return ResultRO.success(socialMessageRO);
            } else if (idContentType.equals(SocialuniContentType.chat)) {


                //获取chat的类型，是不是私聊


                //群聊
                SocialMessageRO socialMessageRO = messageEntity.sendGroupMessage(unionId, msgAddVO.getContent(), msgType);

                System.out.println("fanhuile1111");
                return ResultRO.success(socialMessageRO);

            } else {
                throw new SocialParamsException("错误的会话标识");
            }
        }
    }*/


    public ResultRO<List<SocialMessageRO>> queryMessages(MessageQueryVO queryVO) {
        List<SocialMessageRO> messageVOS = new ArrayList<>();
        String chatIdStr = queryVO.getChatId();
        //msg id也要统一，举报的时候不知道是哪个
//        List<Long> msgIds = queryVO.getMsgIds();
        //前台传入chatId，
        //校验chatId，对应的chatDO是否存在，
        //校验chat下是否存在此user


        //如果穿chatId，需要获取chatUserId，根据chatUserId获取msg

        //chatId操作，和user获取chatUser,判断是否存在

        //chatUserid，获取，判断user是否对应

        //查询所有messageReceive
        //前台没传这个值
        //什么状态的可以查询？chat的状态列表有哪些，各代表什么业务
        //chatUser呢


        //1,从chat页面进入，已有chat

        //首先获取chatId
        //无论是否登陆，都是先获取chatId
        /*if (chatId == null) {
            if (msgIds.size() < 1) {
                return new ResultRO<>(messageVOS);
            }
            Optional<MessageDO> messageDOOptional = messageRepository.findById(msgIds.get(0));
            if (!messageDOOptional.isPresent()) {
                return new ResultRO<>(messageVOS);
            }
            chatId = messageDOOptional.get().getChatId();
        }*/


        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidNotNull(chatIdStr);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功


        //私聊
        if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.user)) {
            Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
            Long beUserId = socialuniUnionIdDo.getSelfSysId();

            //如果用户存在查看会话
            SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(mineUserId, beUserId);
            if (chatUserDO == null) {
                return ResultRO.success(new ArrayList<>());
            }
            List<SocialuniMessageReceiveDO> messageDOS = messageReceiveRepository.findTop30ByChatUserIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(chatUserDO.getId(), MessageReceiveStatus.enable, queryVO.getQueryTime());
            messageVOS = SocialMessageROFactory.messageReceiveDOToVOS(messageDOS);
            return ResultRO.success(messageVOS);
        } else if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.chat)) {
            //则为chatId
            Long chatId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(chatIdStr);

            SocialuniChatDO chatDO = SocialuniChatDOUtil.findByUnionId(chatId);
            if (!chatDO.getStatus().equals(ChatStatus.enable)) {
                throw new SocialBusinessException("会话已被删除");
            }
            List<SocialuniMessageDO> messageDOS = messageRepository.findTop30ByChatIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(chatId, MessageStatus.enable, queryVO.getQueryTime());
            messageVOS = SocialMessageROFactory.messageDOToVOS(messageDOS, SocialuniUserUtil.getMineUserIdAllowNull());
            return ResultRO.success(messageVOS);
        } else {
            throw new SocialParamsException("错误的会话标识");
        }


    }

    public ResultRO<List<SocialMessageRO>> queryNewMessages(SocialuniPageQueryQO<MessageQueryVO> queryQO) {
        MessageQueryVO queryVO = queryQO.getQueryData();

        List<SocialMessageRO> messageVOS = new ArrayList<>();
        String chatIdStr = queryVO.getChatId();

        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidAllowNull(chatIdStr);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        //私聊
        if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.user)) {
            Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
            Long beUserId = socialuniUnionIdDo.getSelfSysId();

            //如果用户存在查看会话
            SocialuniChatUserDO chatUserDO = SocialuniChatUserDOUtil.findByChatIdAndUserId(mineUserId, beUserId);

            if (chatUserDO == null) {
                return ResultRO.success(new ArrayList<>());
            }
            List<SocialuniMessageReceiveDO> messageDOS = messageReceiveRepository.findTop30ByChatUserIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(chatUserDO.getId(), MessageReceiveStatus.enable, queryVO.getQueryTime());
            messageVOS = SocialMessageROFactory.messageReceiveDOToVOS(messageDOS);
            return ResultRO.success(messageVOS);
        } else if (socialuniUnionIdDo.getContentType().equals(SocialuniContentType.chat)) {
            //则为chatId
            Long chatId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(chatIdStr);

            SocialuniChatDO chatDO = SocialuniChatDOUtil.findByUnionId(chatId);
            if (!chatDO.getStatus().equals(ChatStatus.enable)) {
                throw new SocialBusinessException("会话已被删除");
            }
            List<SocialuniMessageDO> messageDOS = messageRepository.findTop30ByChatIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(chatId, MessageStatus.enable, queryVO.getQueryTime());
            messageVOS = SocialMessageROFactory.messageDOToVOS(messageDOS, SocialuniUserUtil.getMineUserIdAllowNull());
            return ResultRO.success(messageVOS);
        } else {
            throw new SocialParamsException("错误的会话标识");
        }
    }
}
