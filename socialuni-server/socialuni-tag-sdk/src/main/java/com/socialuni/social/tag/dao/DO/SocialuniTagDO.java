package com.socialuni.social.tag.dao.DO;

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
@Table(name = "s_community_tag",
        indexes = {
                @Index(columnList = "status"),
                @Index(columnList = "showFront"),
                @Index(columnList = "count"),
                @Index(columnList = "visibleGender"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"}),
        }
)
@Data
@NoArgsConstructor
public class SocialuniTagDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer tagTypeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;
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
    private Long applyUserId;

    private Date createTime;

    private Date updateTime;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    //仅女生可见，也仅女生可发表
    @Column(nullable = false)
    private String visibleGender;

    //是否在前台展示，app产品，不在前台展示
    @Column(columnDefinition = "bit default true")
    private Boolean showFront;
    //devId为唯一是因为根据开发者创建圈子，且不可修改
    private Integer devId;
}
