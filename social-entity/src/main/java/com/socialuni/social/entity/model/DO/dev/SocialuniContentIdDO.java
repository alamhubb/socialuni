package com.socialuni.social.entity.model.DO.dev;

import com.socialuni.social.entity.model.DO.base.CommonBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * 联盟id记录表，
 * 功能，通过联盟唯一id换取id，优化查询速度的作用，否则就要直接使用socialuniId, 速度会慢
 */
@Data
@Entity
@Table(name = "u_socialuni_content_id",
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"socialuniId","contentType","contentId"})
        }
)
public class SocialuniContentIdDO extends CommonBaseDO implements Serializable {
    //联盟id，唯一标识，不得小于32位，全网唯一
    @Column(nullable = false)
    private String socialuniId;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String contentId;
}