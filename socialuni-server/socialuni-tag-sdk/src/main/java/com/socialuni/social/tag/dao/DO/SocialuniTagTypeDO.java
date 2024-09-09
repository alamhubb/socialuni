package com.socialuni.social.tag.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "s_community_tag_type",
        indexes = {
                @Index(columnList = "orderLevel"),
                @Index(columnList = "talkCount")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        })
@Data
@EqualsAndHashCode(callSuper = true)
public class SocialuniTagTypeDO extends SocialuniContentBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    //访问+发帖次数
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer count;
    //本标签共有多少帖子
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer talkCount;

    private Date createTime;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    @Column(columnDefinition = "int default 0")
    private Integer orderLevel;
}
