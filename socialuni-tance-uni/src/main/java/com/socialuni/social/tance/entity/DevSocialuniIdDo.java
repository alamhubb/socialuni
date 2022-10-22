package com.socialuni.social.tance.entity;

import com.socialuni.social.common.repository.CommonBaseDO;
import com.socialuni.social.tance.sdk.model.DevSocialuniIdModel;
import com.socialuni.social.tance.sdk.model.DevTokenModler;
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
@Table(name = "s_socialuni_id",
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"socialuniId"})
        }
)
public class DevSocialuniIdDo extends DevSocialuniIdModel implements Serializable {
    //联盟id，唯一标识，不得小于32位，全网唯一
    @Column(nullable = false)
    private String socialuniId;
}