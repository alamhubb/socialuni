package com.socialuni.social.sdk.dao.DO.circle;

import com.socialuni.social.sdk.constant.socialuni.GenderType;
import com.socialuni.social.common.repository.CommonContentBaseDO;
import com.socialuni.social.sdk.dao.DO.user.SocialuniUserDO;
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
@Table(name = "s_circle",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "showFront"),
                @Index(columnList = "count"),
                @Index(columnList = "visibleGender"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        }
)
@Data
@NoArgsConstructor
public class SocialuniCircleDO extends CommonContentBaseDO implements Serializable {
    private Integer tagTypeId;

    private String name;
    //所在城市名称
    private String cityName;

    private String avatar;

    //访问+发帖次数
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer count;
    //本标签共有多少帖子
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer talkCount;

    /**
     * 申请人
     */
    private Integer applyUserId;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    //仅女生可见，也仅女生可发表
    private String visibleGender;

    //是否在前台展示，app产品，不在前台展示
    private Boolean showFront;
    private Integer devId;

    public SocialuniCircleDO(String name, String description, Integer devId, SocialuniUserDO createUser) {
        this.name = name;
        this.description = description;
        this.applyUserId = 1;
        this.devId = devId;
        this.tagTypeId = 1;
        this.count = 0;
        this.talkCount = 0;
        this.visibleGender = GenderType.all;
    }
}
