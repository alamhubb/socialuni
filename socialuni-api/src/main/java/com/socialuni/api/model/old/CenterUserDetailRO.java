package com.socialuni.api.model.old;

import com.socialuni.social.model.model.RO.user.SocialUserDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class CenterUserDetailRO extends SocialUserDetailRO {

    public CenterUserDetailRO(SocialUserDetailRO userRO) {
        super(userRO);
    }

    public CenterUserDetailRO(CenterUserDetailRO userRO) {
        super(userRO);
    }
}
