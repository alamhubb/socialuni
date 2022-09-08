package com.socialuni.sdk.dao.DO;

import com.socialuni.sdk.dao.DO.base.CommonBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
//                @Index(columnList = "showFront"),
@Entity
@Table(name = "s_school",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        }
)
@Data
@NoArgsConstructor
public class SocialuniSchoolDO extends CommonBaseDO implements Serializable {
    private String name;
    //所在城市名称
    private String cityName;
    private String provinceName;
}
