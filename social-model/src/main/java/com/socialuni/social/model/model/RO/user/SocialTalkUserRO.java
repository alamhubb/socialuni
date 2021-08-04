package com.socialuni.social.model.model.RO.user;

import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialTalkUserRO extends SocialContentUserRO {
    public SocialTalkUserRO(SocialContentUserRO userRO) {
        super(userRO);
    }
}
