package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.social.web.sdk.utils.IpUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
@Data
@Entity
@Table(name = "user_log")
public class UserLogDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //邀请你的用户
    private Integer userId;
    private Date createTime;
    //日志记录内容
    private String content;
    private String hint;
    private String ip;
    private String phoneNum;
    private String authCode;

    public UserLogDO() {
    }

    public UserLogDO(String content, SocialUserDO user) {
        this.userId = user != null ? user.getId() : null;
        this.content = content;
        this.createTime = new Date();
        this.ip = IpUtil.getIpAddr();
    }

    public UserLogDO(String content, SocialUserDO user, String phoneNum) {
        this(content, user);
        this.phoneNum = phoneNum;
    }

    public UserLogDO(String content, SocialUserDO user, String phoneNum, String authCode) {
        this(content, user, phoneNum);
        this.authCode = authCode;
    }
}
