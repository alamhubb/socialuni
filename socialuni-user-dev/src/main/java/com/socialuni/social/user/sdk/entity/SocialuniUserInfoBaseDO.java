package com.socialuni.social.user.sdk.entity;

import com.socialuni.social.user.sdk.model.SocialuniUserInfoBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class SocialuniUserInfoBaseDO extends SocialuniUserInfoBaseModel implements Serializable {
    @Column(nullable = false)
    private Integer userId;

    public SocialuniUserInfoBaseDO(Integer userId) {
        this.userId = userId;
    }
}