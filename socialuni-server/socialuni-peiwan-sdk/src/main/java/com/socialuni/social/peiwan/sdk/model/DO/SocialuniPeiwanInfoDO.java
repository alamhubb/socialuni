package com.socialuni.social.peiwan.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "s_peiwan_info",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "nickname"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId"),
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniPeiwanInfoDO extends SocialuniContentBaseDO {

    private String nickname;
    private String avatar;

    private String city;
    private String district;
    private String skills;
    //纬度
    private Double lat;
    //经度
    private Double lng;
}
