package com.socialuni.entity.model.DO.tag;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */
@Entity
@Table(name = "tag_type",
        indexes = {
                @Index(columnList = "orderLevel"),
                @Index(columnList = "talkCount")
        },
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Data
public class TagTypeDO  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String status;

    //访问+发帖次数
    @Column(columnDefinition = "int default 0")
    private Integer count;
    //本标签共有多少帖子
    @Column(columnDefinition = "int default 0")
    private Integer talkCount;

    private Date createTime;

    private Date updateTime;

    private String description;

    //是否显示微信号,处对象，处微友类型显示微信账号
    private Boolean showWxAccount;

    //是否显示在主页，仅显示在话题下，比如炫富话题不适合上热门首页
    private Boolean showInHome;

    @Column(columnDefinition = "int default 0")
    private Integer orderLevel;
}
