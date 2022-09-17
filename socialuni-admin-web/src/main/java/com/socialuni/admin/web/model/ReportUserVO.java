package com.socialuni.admin.web.model;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import lombok.Data;


@Data
public class ReportUserVO {
    private String avatar;
    private String nickname;
    private String status;
    private Integer id;

    public ReportUserVO() {
    }

    public ReportUserVO(SocialuniUserDO user) {
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.id = user.getUnionId();
    }
}
