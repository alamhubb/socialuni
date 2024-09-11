package com.socialuni.social.common.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class SocialuniUnionContentBaseDO extends SocialuniContentBaseDO implements Serializable {
    @Column(nullable = false)
    private String contentType;
//    @Column(nullable = false)
    @Column
    private Integer devId;
    @Column(nullable = false)
    private Long unionId;
    //内容主体
    @Column(columnDefinition = "text")
    private String content;
    /**
     * 举报次数
     */
    @Column(nullable = false)
    private Integer reportNum;

    //违规类型
    private String violateType;
    /**
     * 删除原因
     */
    private String deleteReason;

    public SocialuniUnionContentBaseDO() {
        this.reportNum = 0;
    }

    public SocialuniUnionContentBaseDO(Long userId) {
        super(userId);
        this.reportNum = 0;
    }


    public SocialuniUnionContentBaseDO(Long userId, String contentType, String content) {
        this(userId);
        this.contentType = contentType;
        this.content = content;
    }

    public SocialuniUnionContentBaseDO(Integer devId, Long userId, Long unionId, String contentType, String content) {
        this(userId, contentType, content);
        this.unionId = unionId;
        this.devId = devId;
    }

    public SocialuniUnionContentBaseDO(Integer devId, Long userId, Long unionId, String contentType) {
        this(devId, userId, unionId, contentType, null);
    }

    @Deprecated
    public Integer getId() {
        return super.getId();
    }

    /*public SocialUnionContentBaseDO(SocialUnionContentBaseDO socialUnionContentBaseDO, String content) {
        this.userId = socialUnionContentBaseDO.getUserId();
        this.unionId = socialUnionContentBaseDO.getUnionId();
        this.contentType = socialUnionContentBaseDO.getContentType();
        this.content = content;
        this.reportNum = socialUnionContentBaseDO.getReportNum();
        this.setStatus(socialUnionContentBaseDO.getStatus());
    }*/
}