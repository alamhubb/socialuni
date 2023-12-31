package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserFollowDetailListRO extends SocialuniUserFollowInfoRO {
    private Date updateTime;

    public SocialuniUserFollowDetailListRO(SocialuniUserFollowInfoRO socialuniUserFollowDetailRO) {
        super(socialuniUserFollowDetailRO);
    }
}
