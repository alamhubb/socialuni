package com.socialuni.social.entity.model.DO.tag;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.GenderType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "tag",
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
public class TagDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tagTypeId;

    private String name;

    private String status;
    private String avatar;

    //访问+发帖次数
    private Integer count;
    //本标签共有多少帖子
    private Integer talkCount;

    /**
     * 申请人
     */
    private Integer applyUserId;

    private Date createTime;

    private Date updateTime;

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

    public TagDO(Integer userId, String tagName, String tagDesc) {
        this.setApplyUserId(userId);
        //先默认为1，以后设置可以更改选择类型
        this.setTagTypeId(1);
        this.setName(tagName);
        this.setAvatar("https://cdxapp-1257733245.cos.ap-beijing.myqcloud.com/qingchi/static/qclogo.jpg!avatar");
        this.setDescription(tagDesc);
        this.setStatus(ContentStatus.enable);
        this.setCount(0);
        this.setTalkCount(0);
        this.setShowFront(true);
        Date curDate = new Date();
        this.setCreateTime(curDate);
        this.setUpdateTime(curDate);
        this.setVisibleGender(GenderType.all);
    }
}
