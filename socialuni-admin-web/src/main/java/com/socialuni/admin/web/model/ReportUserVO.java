package com.socialuni.admin.web.model;

import com.socialuni.sdk.model.DO.user.SocialUserDO;
import lombok.Data;


@Data
public class ReportUserVO {
    private String avatar;
    private String nickname;
    private String status;
    private Integer id;

    public ReportUserVO() {
    }

    public ReportUserVO(SocialUserDO user) {
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.id = user.getId();
    }
}
