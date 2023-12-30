package com.socialuni.social.common.api.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialuniBaseDO implements Serializable {
    @Column(updatable = false)
    private Integer userId;

    public SocialuniUserInfoBaseDO(Integer userId) {
        this.userId = userId;
    }
}