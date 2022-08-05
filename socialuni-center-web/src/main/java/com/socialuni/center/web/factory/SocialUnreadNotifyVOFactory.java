package com.socialuni.center.web.factory;

import com.socialuni.center.web.model.DO.NotifyDO;
import com.socialuni.center.web.model.DO.comment.CommentDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkImgDO;
import com.socialuni.center.web.model.DO.talk.TalkDO;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.center.web.constant.NotifyType;
import com.socialuni.center.web.repository.CommentRepository;
import com.socialuni.center.web.repository.community.TalkImgRepository;
import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.utils.CommentUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.utils.TalkImgDOUtils;
import com.socialuni.center.web.utils.TalkUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-26 16:00
 */
@Component
public class SocialUnreadNotifyVOFactory {
    private static CommentRepository commentRepository;
    private static TalkImgRepository talkImgRepository;
    private static TalkRepository talkRepository;

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        SocialUnreadNotifyVOFactory.talkImgRepository = talkImgRepository;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialUnreadNotifyVOFactory.commentRepository = commentRepository;
    }

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        SocialUnreadNotifyVOFactory.talkRepository = talkRepository;
    }

    public static SocialUnreadNotifyVO newUnreadNotifyVO(UserDO user) {
        SocialUnreadNotifyVO notifyVO = new SocialUnreadNotifyVO();
        if (user != null) {
            notifyVO.setNickname(user.getNickname());
            notifyVO.setAvatar(user.getAvatar());
            notifyVO.setHasRead(false);
//            this.vipFlag = user.getVipFlag();
        }
        return notifyVO;
    }

    public static SocialUnreadNotifyVO newUnreadNotifyVO(NotifyDO notifyDO) {
        UserDO notifyUser = SocialUserUtil.getNotNull(notifyDO.getUserId());
        SocialUnreadNotifyVO notifyVO = SocialUnreadNotifyVOFactory.newUnreadNotifyVO(notifyUser);

        Integer commentId = notifyDO.getCommentId();
        CommentDO commentDO = CommentUtils.get(commentId);
        TalkDO talk = TalkUtils.get(commentDO.getTalkId());

        //赋值
        notifyVO.setTalkId(talk.getId().toString());
        notifyVO.setContent(commentDO.getContent());
        notifyVO.setCreateTime(commentDO.getCreateTime());
        notifyVO.setHasRead(notifyDO.getHasRead());

        switch (notifyDO.getType()) {
            case NotifyType.talk_comment:
                List<SocialTalkImgDO> socialTalkImgDOS = TalkImgDOUtils.findTop3ByTalkId(talk.getId());
//                List<TalkImgDO> talkImgDOS = talk.getImgs();
                if (socialTalkImgDOS.size() > 0) {
                    notifyVO.setReplyImg(socialTalkImgDOS.get(0).getSrc());
                } else {
                    notifyVO.setReplyContent(talk.getContent());
                }
                break;
            case NotifyType.comment_comment:
                CommentDO optionalCommentDO1 = CommentUtils.get(commentDO.getParentCommentId());
                notifyVO.setReplyContent(optionalCommentDO1.getContent());
                break;
            case NotifyType.reply_comment:
                CommentDO optionalCommentDO2 = CommentUtils.get(commentDO.getReplyCommentId());
                notifyVO.setReplyContent(optionalCommentDO2.getContent());
                break;
        }
        return notifyVO;
    }

    public static List<SocialUnreadNotifyVO> unreadNotifyDOToVOS(List<NotifyDO> notifyDOS) {
        return ListConvertUtil.toList(SocialUnreadNotifyVOFactory::newUnreadNotifyVO, notifyDOS);
    }
}
