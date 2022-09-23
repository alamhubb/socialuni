package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.constant.platform.UniappProviderType;
import com.socialuni.social.sdk.dao.DO.NotifyDO;
import com.socialuni.social.sdk.dao.DO.community.comment.SocialuniCommentDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.social.sdk.dao.utils.content.SocialuniCommentDOUtil;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.platform.qq.QQConst;
import com.socialuni.social.sdk.logic.platform.weixin.WxConst;
import com.socialuni.social.sdk.model.PushMsgDTO;
import com.socialuni.social.sdk.model.PushNotifyVO;
import com.socialuni.social.sdk.model.PushValue;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
public class CommentPushUtils {
    //动态评论通知
    public static PushMsgDTO getCommentPushDTO(String platform, NotifyDO notify, SocialuniUserDO requestUser) {
        SocialuniCommentDO comment = SocialuniCommentDOUtil.getNotCommentNull(notify.getCommentId());
        SocialuniTalkDO talk = SocialuniTalkDOUtil.getTalkNotNull(comment.getTalkId());
        SocialuniCommentDO replyComment;
        if (comment.getReplyCommentId() == null) {
            replyComment = SocialuniCommentDOUtil.getNotCommentNull(comment.getParentCommentId());
        } else {
            replyComment = SocialuniCommentDOUtil.getNotCommentNull(comment.getReplyCommentId());
        }

        PushNotifyVO pushNotifyVO = new PushNotifyVO();
        //构建基础数据
        pushNotifyVO.setContent(new PushValue(StringUtils.substring(comment.getContent(), 0, 20)));
        pushNotifyVO.setNickname(new PushValue(requestUser.getNickname()));
        pushNotifyVO.setDate(new PushValue(DateUtils.simpleTimeFormat.format(comment.getCreateTime())));
        pushNotifyVO.setBeContent(new PushValue(StringUtils.substring(replyComment.getContent(), 0, 20)));
        pushNotifyVO.setBeNickname(new PushValue(SocialuniUserUtil.getUserNotNull(replyComment.getUserId()).getNickname()));

        HashMap<String, Object> data = new HashMap<>();
        PushMsgDTO pushMsgDTO = null;
//        String page = AppConfigConst.notify_skip_page + UnionIdDbUtil.createTalkUid(talk.getId(), notify.getReceiveUserId());
        String page = AppConfigConst.notify_skip_page;
        if (platform.equals(UniappProviderType.qq)) {
            //评论内容
            data.put("keyword5", pushNotifyVO.getContent());
            //评论用户
            data.put("keyword3", pushNotifyVO.getNickname());
            //评论时间
            data.put("keyword4", pushNotifyVO.getDate());
            //帖子内容
            data.put("keyword1", pushNotifyVO.getBeContent());
            //评论者
            data.put("keyword2", pushNotifyVO.getBeNickname());

            pushMsgDTO = new PushMsgDTO(QQConst.comment_template_id, page, data, "keyword5.DATA");
        } else if (platform.equals(UniappProviderType.wx)) {
            //评论内容
            data.put("thing2", pushNotifyVO.getContent());
            //评论用户
            data.put("thing3", pushNotifyVO.getNickname());
            //评论时间
            data.put("time4", pushNotifyVO.getDate());
            //帖子内容
            data.put("thing1", pushNotifyVO.getBeContent());

            pushMsgDTO = new PushMsgDTO(WxConst.comment_template_id, page, data);
        }
        return pushMsgDTO;
    }
}
