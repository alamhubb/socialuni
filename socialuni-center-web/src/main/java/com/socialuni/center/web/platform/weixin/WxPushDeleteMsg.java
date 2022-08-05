package com.socialuni.center.web.platform.weixin;

/**
 * @author qinkaiyuan
 * @date 2020-03-22 2:04
 */
public class WxPushDeleteMsg {
    /*public static void wxPushDeleteTalk(String platform, String openId, NotifyDO notify) {
        //如果评论
        String accessToken = WxUtil.getAccessToken();
        HashMap<String, Object> data = getWxPushDeleteTalkContent(notify);
        String template_id = WxConst.delete_talk_template_id;
        PushMsgDTO pushMsgDTO = new PushMsgDTO(accessToken, openId, template_id, null, data);
        WxUtil.wxPushMsgCommon(platform, notify, accessToken, pushMsgDTO);
    }

    public static HashMap<String, Object> getWxPushDeleteTalkContent(NotifyDO notify) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Integer talkId = notify.getTalk();
        //审核内容
        dataMap.put("thing5", new PushValue(StringUtils.substring(talk.getContent(), 0, 20)));
        //审核结果
        dataMap.put("phrase1", new PushValue("违规删除"));
        //删除时间
        dataMap.put("date4", new PushValue(DateUtils.simpleTimeFormat.format(notify.getCreateTime())));
        //删除人
        dataMap.put("name7", new PushValue(notify.getUser().getNickname()));
        //备注
        dataMap.put("thing8", new PushValue(StringUtils.substring(talk.getDeleteReason(), 0, 20)));
        return dataMap;
    }


    public static void wxPushDeleteComment(String platform, String openId, NotifyDO notify) {
        //如果评论
        String accessToken = WxUtil.getAccessToken();
        HashMap<String, Object> data = getWxPushDeleteCommentContent(notify);
        String template_id = WxConst.delete_comment_template_id;
        PushMsgDTO pushMsgDTO = new PushMsgDTO(accessToken, openId, template_id, null, data);
        WxUtil.wxPushMsgCommon(platform, notify, accessToken, pushMsgDTO);
    }

    public static HashMap<String, Object> getWxPushDeleteCommentContent(NotifyDO notify) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Integer commentId = notify.getComment();
        //审核内容
        dataMap.put("thing5", new PushValue(StringUtils.substring(comment.getContent(), 0, 20)));
        //审核结果
        dataMap.put("phrase1", new PushValue("违规删除"));
        //删除时间
        dataMap.put("date4", new PushValue(DateUtils.simpleTimeFormat.format(notify.getCreateTime())));
        //删除人
        dataMap.put("name7", new PushValue(notify.getUser().getNickname()));
        //备注
        dataMap.put("thing8", new PushValue(StringUtils.substring(comment.getDeleteReason(), 0, 20)));
        return dataMap;
    }*/
}
