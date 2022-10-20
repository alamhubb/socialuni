package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.sdk.constant.NotifyType;
import com.socialuni.social.sdk.dao.DO.NotifyDO;
import com.socialuni.social.community.sdk.model.SocialuniCommentModel;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.community.sdk.api.CommentInterface;
import com.socialuni.social.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.social.community.sdk.api.TalkInterface;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkImgDOUtil;
import com.socialuni.social.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-26 16:00
 */
@Component
public class SocialUnreadNotifyVOFactory {
    private static CommentInterface commentApi;
    private static TalkImgRepository talkImgRepository;
    private static TalkInterface talkApi;

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        SocialUnreadNotifyVOFactory.talkImgRepository = talkImgRepository;
    }

    @Resource
    public void setCommentRepository(CommentInterface commentApi) {
        SocialUnreadNotifyVOFactory.commentApi = commentApi;
    }

    @Resource
    public void setTalkRepository(TalkInterface talkApi) {
        SocialUnreadNotifyVOFactory.talkApi = talkApi;
    }

    public static SocialUnreadNotifyVO newUnreadNotifyVO(SocialuniUserModel user) {
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
        SocialuniUserModel notifyUser = SocialuniUserUtil.getUserNotNull(notifyDO.getUserId());
        SocialUnreadNotifyVO notifyVO = SocialUnreadNotifyVOFactory.newUnreadNotifyVO(notifyUser);

        Integer commentId = notifyDO.getCommentId();
        SocialuniCommentModel commentDO = SocialuniCommentDOUtil.getNotCommentNull(commentId);
        SocialuniTalkModel talk = SocialuniTalkDOUtil.getTalkNotNull(commentDO.getTalkId());

        //赋值
        notifyVO.setTalkId(talk.getUnionId().toString());
        notifyVO.setContent(commentDO.getContent());
        notifyVO.setCreateTime(commentDO.getCreateTime());
        notifyVO.setHasRead(notifyDO.getHasRead());

        switch (notifyDO.getType()) {
            case NotifyType.talk_comment:
                List<SocialuniTalkImgModel> socialTalkImgDOS = SocialuniTalkImgDOUtil.getTalkImgsTop3(talk.getUnionId());
//                List<TalkImgDO> talkImgDOS = talk.getImgs();
                if (socialTalkImgDOS.size() > 0) {
                    notifyVO.setReplyImg(socialTalkImgDOS.get(0).getSrc());
                } else {
                    notifyVO.setReplyContent(talk.getContent());
                }
                break;
            case NotifyType.comment_comment:
                SocialuniCommentModel optionalCommentDO1 = SocialuniCommentDOUtil.getNotCommentNull(commentDO.getParentCommentId());
                notifyVO.setReplyContent(optionalCommentDO1.getContent());
                break;
            case NotifyType.reply_comment:
                SocialuniCommentModel optionalCommentDO2 = SocialuniCommentDOUtil.getNotCommentNull(commentDO.getReplyCommentId());
                notifyVO.setReplyContent(optionalCommentDO2.getContent());
                break;
        }
        return notifyVO;
    }

    public static List<SocialUnreadNotifyVO> unreadNotifyDOToVOS(List<NotifyDO> notifyDOS) {
        return ListConvertUtil.toList(SocialUnreadNotifyVOFactory::newUnreadNotifyVO, notifyDOS);
    }
}
