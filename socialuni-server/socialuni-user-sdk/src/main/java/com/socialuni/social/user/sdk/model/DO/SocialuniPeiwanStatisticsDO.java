package com.socialuni.social.user.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_peiwan_statistics",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userIp"),
                @Index(columnList = "inviterCode"),
        }
)
@Data
public class SocialuniPeiwanStatisticsDO extends SocialuniBaseDO {
    private String inviterCode;
    private String referer;
    private String detailId;
    @Column(columnDefinition = "longtext")
    private String detail;
    private String userIp;
}
