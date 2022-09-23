package com.socialuni.social.sdk.dao.DO.user;

import com.socialuni.social.sdk.dao.DO.SocialContentBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
public class SocialUnionContentBaseDO extends SocialContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private Integer unionId;
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

    public SocialUnionContentBaseDO() {
        this.reportNum = 0;
    }

    public SocialUnionContentBaseDO(Integer userId, String contentType, String content) {
        this.reportNum = 0;
        this.userId = userId;
        this.contentType = contentType;
        this.content = content;
    }

    public SocialUnionContentBaseDO(SocialUnionContentBaseDO socialUnionContentBaseDO, String content) {
        this.userId = socialUnionContentBaseDO.getUserId();
        this.unionId = socialUnionContentBaseDO.getUnionId();
        this.contentType = socialUnionContentBaseDO.getContentType();
        this.content = content;
        this.reportNum = socialUnionContentBaseDO.getReportNum();
        this.setStatus(socialUnionContentBaseDO.getStatus());
    }
}