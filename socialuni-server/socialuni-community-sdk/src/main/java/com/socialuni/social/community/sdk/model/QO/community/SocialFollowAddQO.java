package com.socialuni.social.community.sdk.model.QO.community;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
public class SocialFollowAddQO {
    /**
     * 被关注的用户id
     */
    @NotNull(message = "入参为空异常")
    private Long beUserId;
}
