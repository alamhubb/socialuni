package com.socialuni.social.sdk.im.controller;

import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.im.api.feign.SocialuniMessageAPI;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.im.api.model.RO.SocialMessageRO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageReceiveDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageReceiveRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniMessageRepository;
import com.socialuni.social.sdk.im.enumeration.ChatStatus;
import com.socialuni.social.sdk.im.logic.foctory.SocialMessageROFactory;
import com.socialuni.social.im.api.model.QO.MessageQueryVO;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.sdk.im.enumeration.MessageReceiveStatus;
import com.socialuni.social.sdk.im.enumeration.MessageStatus;
import com.socialuni.social.sdk.im.utils.SocialuniChatUserDOUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("socialuni/message")
public class SocialuniMessageController implements SocialuniMessageAPI {
    @Resource
    private SocialuniChatRepository chatRepository;
    @Resource
    private SocialuniMessageRepository messageRepository;
    @Resource
    private SocialuniMessageReceiveRepository messageReceiveRepository;
    @Resource
    private ChatUserVerify chatUserVerify;
    @Resource
    private SocialuniMessageService messageService;

    @Override
    public ResultRO<SocialMessageRO> sendMsg(MessageAddVO messageAddVO) {
        return messageService.sendMsg(messageAddVO);
    }
    /*
     */

    /**
     * toDO 这里有问题，都统一用的 msgid
     *
     * @param queryVO
     * @return
     */
    public ResultRO<List<SocialMessageRO>> queryMessages(@RequestBody @Valid MessageQueryVO queryVO) {
        return messageService.queryMessages(queryVO);
    }

    @Override
    public ResultRO<List<SocialMessageRO>> queryNewMessages(SocialuniPageQueryQO<MessageQueryVO> queryQO) {
        return messageService.queryNewMessages(queryQO);
    }
    /*



    @PostMapping("sendMsg")
    public ResultRO<MessageVO> sendMsg(@RequestBody @Valid @NotNull MessageAddVO msgAddVO) throws IOException {
        //只需要返回自己的
        return messageService.sendMsg(msgAddVO);
    }

    @PostMapping("deleteMsg")
    @ResponseBody
    public ResultRO<Object> deleteMsg(SocialuniUserDo user, @RequestBody @Valid MsgDeleteVO msgVO) {
        *//**
     * 删除动态操作，
     * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
     * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
     *//*
        Optional<MessageDO> optionalMsgDO = messageRepository.findFirstOneByIdAndStatusIn(msgVO.getMsgId(), ContentStatus.otherCanSeeContentStatus);
        if (!optionalMsgDO.isPresent()) {
            return new ResultRO<>("无法删除不存在的消息");
        }
        MessageDO msgDO = optionalMsgDO.get();
        //如果是系统管理员操作,则把发表动态的用户封禁，如果用户本身也是管理员则不封禁，存在管理员自己删自己的情况
        if (UserType.system.equals(user.getType())) {
            //管理员不能删除内容了
            *//*if (StringUtils.isEmpty(msgVO.getDeleteReason())) {
                return new ResultRO<>("必须填写删除原因");
            }
            //改为删除状态，和写上删除原因
            msgDO.setStatus(CommonStatus.delete);
            msgDO.setDeleteReason(msgVO.getDeleteReason());
            Date curDate = new Date();
            msgDO.setUpdateTime(curDate);
            SocialuniUserDo violationUser = msgDO.getUser();
            //不为管理员自己删自己
            //且封禁撞他不为空，且为封禁，才执行封禁用户操作
            if (!ObjectUtils.isEmpty(msgVO.getViolation()) && msgVO.getViolation()) {
                violationUser.setStatus(CommonStatus.violation);
                msgDO.setStatus(CommonStatus.violation);
                //如果封禁的话，要改一下删除原因
                String deleteReason = "账号违规被封禁，请联系客服处理，删除原因：" + msgVO.getDeleteReason();
                //修改 D O的删除原因
                msgDO.setDeleteReason(deleteReason);
                violationUser.setViolationReason(msgVO.getDeleteReason());
                violationUser.setViolationCount(violationUser.getViolationCount() + 1);
                violationUser.setUpdateTime(curDate);
            }
            //给用户发送被封通知
            NotifyDO notifyDO = new NotifyDO(user, violationUser, NotifyType.delete_msg);
//            toDO notifyDO.setMessage(msgDO);
            notifyDO = notifyRepository.save(notifyDO);
            //推送消息
            notifyService.sendNotifies(Collections.singletonList(notifyDO));*//*
            //不是管理员的话就必须是自己删除自己
        } else {
            //是否是自己删除自己的动态
            if (!msgDO.getUserId().equals(user.getId())) {
                QingLogger.logger.warn("有人尝试删除不属于自己的消息,用户名:{},id:{},尝试删除msgId：{}", user.getNickname(), user.getId(), msgDO.getId());
                return new ResultRO<>("系统异常，无法删除不属于自己的动态");
            }
            msgDO.setStatus(ContentStatus.delete);
            msgDO.setDeleteReason("用户自行删除");
        }
        msgDO.setUpdateTime(new Date());
        messageRepository.save(msgDO);
        return new ResultRO<>();
    }*/
}
