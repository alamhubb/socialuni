package com.socialuni.social.user.sdk.platform;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-03-21 23:51
 */
@Data
public class PushMsgDTO {
    // * 会话标示
    private String access_token;
    // * 小程序 appId
    private String appid;
    // * 接收者（用户）的 openid
    private String touser;
    // * 所需下发的模板消息的id
    private String template_id;
    //跳转的页面
    private String page;
    //表单提交场景需要
    private String form_id;
    //模板内容，模板内容，要求字段数量和模板本身的字段数量一致。具体格式请参考示例。
    private Object data;
    //高亮的关键字
    private String emphasis_keyword;


    public PushMsgDTO(String template_id, Object data) {
        this.template_id = template_id;
        this.data = data;
    }

    public PushMsgDTO(String template_id, String page, Object data) {
        this(template_id, data);
        this.page = page;
    }

    public PushMsgDTO(String template_id, Object data, String emphasis_keyword) {
        this(template_id, data);
        this.emphasis_keyword = emphasis_keyword;
    }

    public PushMsgDTO(String template_id, String page, Object data, String emphasis_keyword) {
        this(template_id, page, data);
        this.emphasis_keyword = emphasis_keyword;
    }


    /*public PushMsgDTO() {

    }*/
}
