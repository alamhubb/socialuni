package com.socialuni.social.entity.model.DO.circle;

import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.base.CommonContentBaseDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "s_circle",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "showFront"),
                @Index(columnList = "count"),
                @Index(columnList = "visibleGender"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"}),
                @UniqueConstraint(columnNames = {"devId"})
        }
)
@Data
@NoArgsConstructor
public class SocialCircleDO extends CommonContentBaseDO implements Serializable {
    private Integer tagTypeId;

    private String name;

    private String avatar;

    //访问+发帖次数
    private Integer count;
    //本标签共有多少帖子
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

    public SocialCircleDO(String name, String description, Integer devId, UserDO createUser) {
        this.name = name;
        this.description = description;
        this.applyUserId = createUser.getId();
        this.devId = devId;
        this.tagTypeId = 1;
        this.count = 0;
        this.talkCount = 0;
        this.visibleGender = GenderType.all;
    }
}
