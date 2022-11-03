package com.socialuni.social.common.sdk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@MappedSuperclass
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialuniBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer userId;

    public SocialuniUserInfoBaseDO(Integer userId) {
        this.userId = userId;
    }
}