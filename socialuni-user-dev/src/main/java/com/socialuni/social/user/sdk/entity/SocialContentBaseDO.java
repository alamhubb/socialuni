package com.socialuni.social.user.sdk.entity;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Table(indexes = {
        @Index(columnList = "status"),
})
@Data
@NoArgsConstructor
public class SocialContentBaseDO extends SocialuniUserInfoBaseDO implements Serializable {
    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public SocialContentBaseDO(Integer userId) {
        super(userId);
        this.updateTime = super.getCreateTime();
        this.status = CommonStatus.enable;
    }
}