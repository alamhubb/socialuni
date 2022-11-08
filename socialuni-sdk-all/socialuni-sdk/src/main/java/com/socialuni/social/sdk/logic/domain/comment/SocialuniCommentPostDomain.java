package com.socialuni.social.sdk.logic.domain.comment;

import com.socialuni.social.community.sdk.entity.SocialuniCommentDO;
import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.sdk.constant.SocialuniConst;
import com.socialuni.social.user.sdk.constant.UserType;
import com.socialuni.social.sdk.dao.store.SocialTagRedis;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.sdk.logic.domain.report.SoicialuniSystemPreCheckReportDomainDOUtil;
import com.socialuni.social.sdk.logic.entity.comment.SocialPostCommentEntity;
import com.socialuni.social.sdk.logic.factory.SocialCommentROFactory;
import com.socialuni.social.user.sdk.utils.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialuniCommentPostDomain {
    @Resource
    private SocialPostCommentEntity socialPostCommentEntity;
    @Resource
    private SocialTagRedis socialTagRedis;

    public SocialuniCommentRO postComment(SocialuniCommentPostQO addQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer talkId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addQO.getTalkId());

        List<?  extends TagDO> TagDOs = socialTagRedis.getTagsByTalkId(talkId);
        List<String> tagNames = TagDOs.stream().map(TagDO::getName).collect(Collectors.toList());

        //系统管理员不校验相关内容
        if (!UserType.system.equals(mineUser.getType())) {
            //不为开发环境，则校验内容
            if (!tagNames.contains(SocialuniConst.devEnvTagName)) {
                //校验用户
                SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable(mineUser);
            }
            SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(addQO.getContent());
        }

        //评论暂时不需要校园名称
       /* if (SocialuniAppConfig.getAppConfig().getMustSetSchoolCanPost()) {
            SocialuniUserExpandDOUtil.getUserSchoolNameNotNull(SocialuniUserUtil.getMineUserIdNotNull());
        }*/

        //校验结果
        //校验时候，访问了数据库，存储了talk、parent、reply这些值，方便以后使用，传输使用
        //保存comment，内部关联保存了talk、parentComment、replyComment

        SocialuniCommentDO commentDO = socialPostCommentEntity.saveComment(addQO, mineUser.getUnionId());

        // 校验是否触发关键词
        SoicialuniSystemPreCheckReportDomainDOUtil.systemPreCheckReport(commentDO);

        //如果不为待审核，才发送通知
        //注释通知相关功能
        /*if (commentDO.getStatus().equals(ContentStatus.enable)) {
            List<NotifyDO> notifyDOS = notifyDomain.saveCreateCommentNotifies(commentDO, commentAddLineTransfer.getTalk(), commentAddLineTransfer.getParentComment(), commentAddLineTransfer.getReplyComment(), mineUser);

            //推送消息
            notifyDomain.sendNotifies(notifyDOS, mineUser);
        }*/

        return SocialCommentROFactory.newTalkCommentRO(mineUser, commentDO, false);
    }


    //校验添加新增comment的评论是否正确
    /*private CommentAddLineTransfer checkCommentAddVO(SocialuniUserDo requestUser, SocialCommentPostQO addVO) {
        //校验内容是否违规
        modelContentCheck.checkUserAndContent(addVO.getContent(), requestUser);

        Integer talkId = addVO.getTalkId();
        //因为与下面有关联所以拿到了上面
        SocialTalkDO talkDO = talkRepository.findOneByUnionId(talkId);
        if (talkDO == null) {
            throw new SocialParamsException("无法评论不存在的动态");
        }
        //不为系统用户才校验
        if (!UserType.system.equals(requestUser.getType())) {
            if (!ObjectUtils.isEmpty(talkDO.getGlobalTop()) && talkDO.getGlobalTop() > 0) {
                throw new SocialParamsException("禁止评论官方置顶");
            }
        }

        SocialCommentDO parentCommentDO = null;
        if (addVO.getCommentId() != null) {
            parentCommentDO = commentRepository.findOneByUnionId(addVO.getCommentId());
            if (parentCommentDO == null) {
                throw new SocialParamsException("无法回复不存在的评论");
            }
        }
        //得到回复的评论
        SocialCommentDO replyCommentDO = null;
        if (addVO.getReplyCommentId() != null) {
            replyCommentDO = commentRepository.findOneByUnionId(addVO.getReplyCommentId());
            if (replyCommentDO == null) {
                throw new SocialParamsException("无法回复不存在的子评论");
            }
        }
        return new CommentAddLineTransfer(talkDO, parentCommentDO, replyCommentDO);
    }*/
}
