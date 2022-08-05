package com.socialuni.center.web.platform.qq;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
public class QQPushDeleteMsg {
    /*public static HashMap<String, Object> getQQPushDeleteTalkContent(NotifyDO notify) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Integer talkId = notify.getTalk();
        //删除原因
        dataMap.put("keyword3", new PushValue(talk.getDeleteReason()));
        //删除内容
        dataMap.put("keyword5", new PushValue(talk.getContent()));
        //删除时间
        dataMap.put("keyword4", new PushValue(DateUtils.simpleTimeFormat.format(notify.getCreateTime())));
        //删除人
        dataMap.put("keyword1", new PushValue(notify.getUser().getNickname()));
        //备注
        dataMap.put("keyword2", new PushValue(ErrorMsg.CONTACT_SERVICE));
        return dataMap;
    }

    public static void qqPushDeleteTalk(String platform, String openId, NotifyDO notify) {
        //如果评论
        String accessToken = QQUtil.getAccessToken();
        HashMap<String, Object> data = getQQPushDeleteTalkContent(notify);
        String emphasis_keyword = "keyword3.DATA";
        QQUtil.qqPushMsgCommon(platform, openId, notify, accessToken, QQConst.delete_talk_template_id, data, emphasis_keyword);
    }


    public static void qqPushDeleteComment(String platform, String openId, NotifyDO notify) {
        //如果评论
        String accessToken = QQUtil.getAccessToken();
        HashMap<String, Object> data = getQQPushDeleteCommentContent(notify);
        String emphasis_keyword = "keyword3.DATA";
        QQUtil.qqPushMsgCommon(platform, openId, notify, accessToken, QQConst.delete_comment_template_id,  data, emphasis_keyword);
    }

    public static HashMap<String, Object> getQQPushDeleteCommentContent(NotifyDO notify) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Integer commentId = notify.getComment();
        //删除原因
        dataMap.put("keyword3", new PushValue(comment.getDeleteReason()));
        //删除内容
        dataMap.put("keyword5", new PushValue(comment.getContent()));
        //删除时间
        dataMap.put("keyword4", new PushValue(DateUtils.simpleTimeFormat.format(notify.getCreateTime())));
        //删除人
        dataMap.put("keyword1", new PushValue(notify.getUser().getNickname()));
        //备注
        dataMap.put("keyword2", new PushValue(ErrorMsg.CONTACT_SERVICE));
        return dataMap;
    }*/
}
