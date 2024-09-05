package com.socialuni.social.sdk.logic.factory;

import com.socialuni.social.common.sdk.model.RO.app.SocialUnreadNotifyVO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniCommentDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.common.sdk.dao.DO.NotifyDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.community.sdk.dao.SocialuniCommentDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDOUtil;
import com.socialuni.social.community.sdk.dao.SocialuniTalkImgDOUtil;
import com.socialuni.social.sdk.im.enumeration.SocialuniNotifyDomainType;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-26 16:00
 */
@Component
public class SocialUnreadNotifyVOFactory {

    public static SocialUnreadNotifyVO newUnreadNotifyVO(SocialuniUserDo user) {
        SocialUnreadNotifyVO notifyVO = new SocialUnreadNotifyVO();
        if (user != null) {
            notifyVO.setNickname(user.getNickname());
            notifyVO.setUserId(SocialuniUnionIdFacede.getUuidByUnionIdNotNull(user.getUserId()));
            notifyVO.setAvatar(user.getAvatar());
            notifyVO.setHasRead(false);
//            this.vipFlag = user.getVipFlag();
        }
        return notifyVO;
    }

    public static SocialUnreadNotifyVO newUnreadNotifyVO(NotifyDO notifyDO) {
        SocialuniUserDo notifyUser = SocialuniUserUtil.getUserNotNull(notifyDO.getUserId());
        SocialUnreadNotifyVO notifyVO = SocialUnreadNotifyVOFactory.newUnreadNotifyVO(notifyUser);
        notifyVO.setHasRead(notifyDO.getHasRead());
        notifyVO.setCreateTime(notifyDO.getCreateTime());

        switch (notifyDO.getDomainType()) {
            case SocialuniNotifyDomainType.cp:
//                notifyVO.setContent(HaxunSendBindNotifyDomain.);
            case SocialuniNotifyDomainType.comment:
                return SocialUnreadNotifyVOFactory.getCommentDomainNotify(notifyVO, notifyDO);

        }
        return notifyVO;
    }


    public static SocialUnreadNotifyVO getCommentDomainNotify(SocialUnreadNotifyVO notifyVO, NotifyDO notifyDO) {
        Long commentId = notifyDO.getContentId();
        SocialuniCommentDO commentDO = SocialuniCommentDOUtil.getNotCommentNull(commentId);
        SocialuniTalkDO talk = SocialuniTalkDOUtil.getTalkNotNull(commentDO.getTalkId());

        String talkUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(talk.getUnionId());

        //赋值
        notifyVO.setContentId(talkUid);
        notifyVO.setContent(commentDO.getContent());
        notifyVO.setCreateTime(commentDO.getCreateTime());

        switch (notifyDO.getDomainType()) {
            case NotifyType.talk_comment:
                List<SocialuniTalkImgDO> socialTalkImgDOS = SocialuniTalkImgDOUtil.getTalkImgsTop3(talk.getUnionId());
//                List<TalkImgDO> talkImgDOS = talk.getImgs();
                if (socialTalkImgDOS.size() > 0) {
                    notifyVO.setReplyImg(socialTalkImgDOS.get(0).getSrc());
                } else {
                    notifyVO.setReplyContent(talk.getContent());
                }
                break;
            case NotifyType.comment_comment:
                SocialuniCommentDO optionalCommentDO1 = SocialuniCommentDOUtil.getNotCommentNull(commentDO.getParentCommentId());
                notifyVO.setReplyContent(optionalCommentDO1.getContent());
                break;
            case NotifyType.reply_comment:
                SocialuniCommentDO optionalCommentDO2 = SocialuniCommentDOUtil.getNotCommentNull(commentDO.getReplyCommentId());
                notifyVO.setReplyContent(optionalCommentDO2.getContent());
                break;
        }
        return notifyVO;
    }

    public static List<SocialUnreadNotifyVO> unreadNotifyDOToVOS(List<NotifyDO> notifyDOS) {
        return ListConvertUtil.toList(SocialUnreadNotifyVOFactory::newUnreadNotifyVO, notifyDOS);
    }
}
