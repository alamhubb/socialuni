package com.socialuni.center.web.utils;


import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.constant.platform.UniappProviderType;
import com.socialuni.center.web.model.PushMsgDTO;
import com.socialuni.center.web.model.PushNotifyVO;
import com.socialuni.center.web.model.PushValue;
import com.socialuni.center.web.platform.qq.QQConst;
import com.socialuni.center.web.platform.weixin.WxConst;
import com.socialuni.center.web.model.DO.NotifyDO;
import com.socialuni.center.web.model.DO.comment.CommentDO;
import com.socialuni.center.web.model.DO.talk.TalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
public class TalkCommentPushUtils {
    //动态评论通知
    public static PushMsgDTO getTalkPushDTO(String provider, NotifyDO notify, SocialUserDO requestUser) {
        CommentDO comment = CommentUtils.get(notify.getCommentId());
        TalkDO talk = TalkUtils.get(comment.getTalkId());

        PushNotifyVO pushNotifyVO = new PushNotifyVO();
        //构建基础数据
        pushNotifyVO.setContent(new PushValue(StringUtils.substring(comment.getContent(), 0, 20)));
        pushNotifyVO.setNickname(new PushValue(requestUser.getNickname()));
        pushNotifyVO.setDate(new PushValue(DateUtils.simpleTimeFormat.format(comment.getCreateTime())));
        pushNotifyVO.setBeContent(new PushValue(StringUtils.substring(talk.getContent(), 0, 20)));

        HashMap<String, Object> data = new HashMap<>();
        PushMsgDTO pushMsgDTO = null;
//        String page = AppConfigConst.notify_skip_page + UnionIdDbUtil.createTalkUid(talk.getId(), notify.getReceiveUserId());
        String page = AppConfigConst.notify_skip_page;
        if (provider.equals(UniappProviderType.qq)) {
            //评论内容
            data.put("keyword3", pushNotifyVO.getContent());
            //评论用户
            data.put("keyword4", pushNotifyVO.getNickname());
            //评论时间
            data.put("keyword1", pushNotifyVO.getDate());
            //帖子内容
            data.put("keyword2", pushNotifyVO.getBeContent());
            pushMsgDTO = new PushMsgDTO(QQConst.talk_template_id, page, data, "keyword3.DATA");
        } else if (provider.equals(UniappProviderType.wx)) {
            //评论内容
            data.put("thing2", pushNotifyVO.getContent());
            //评论用户
            data.put("thing5", pushNotifyVO.getNickname());
            //评论时间
            data.put("time3", pushNotifyVO.getDate());
            //帖子内容
            data.put("thing6", pushNotifyVO.getBeContent());
            pushMsgDTO = new PushMsgDTO(WxConst.talk_template_id, page, data);
        }
        return pushMsgDTO;
    }
}
