package com.socialuni.social.common.api.entity;

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
public class SocialuniUserContactBaseDO extends SocialuniContentBaseDO implements Serializable {
    @Column(nullable = false)
    private Integer beUserId;

    public SocialuniUserContactBaseDO(SocialuniUserContactBaseDO socialuniUserContactBaseDO) {
        super(socialuniUserContactBaseDO.getUserId());
        this.beUserId = socialuniUserContactBaseDO.getBeUserId();
    }
    public SocialuniUserContactBaseDO(Integer userId, Integer beUserId) {
        super(userId);
        this.beUserId = beUserId;
    }
}