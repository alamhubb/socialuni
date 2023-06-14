package com.socialuni.social.admin.model;

import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.Data;


@Data
public class ReportUserVO {
    private String avatar;
    private String nickname;
    private String status;
    private Integer id;

    public ReportUserVO() {
    }

    public ReportUserVO(SocialuniUserDo user) {
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.id = user.getUnionId();
    }
}
