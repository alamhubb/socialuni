package com.socialuni.social.community.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
//                @Index(columnList = "showFront"),
@Entity
@Table(name = "s_community_school",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialuniSchoolDO extends SocialuniBaseDO implements Serializable {
    private String name;
    //所在城市名称
    private String cityName;
    private String provinceName;
}
