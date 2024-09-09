package com.socialuni.social.common.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class SocialuniUpdateTimeContentBaseDO extends SocialuniUserInfoBaseDO implements Serializable {
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date updateTime;

    public SocialuniUpdateTimeContentBaseDO() {
        this.updateTime = super.getCreateTime();
    }

    public SocialuniUpdateTimeContentBaseDO(Long userId) {
        super(userId);
        this.updateTime = super.getCreateTime();
    }
}