package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniUserFollowDetailListRO extends SocialuniUserFollowDetailRO {
    private Date updateTime;

    public SocialuniUserFollowDetailListRO(SocialuniUserFollowDetailRO socialuniUserFollowDetailRO) {
        super(socialuniUserFollowDetailRO);
    }
}
