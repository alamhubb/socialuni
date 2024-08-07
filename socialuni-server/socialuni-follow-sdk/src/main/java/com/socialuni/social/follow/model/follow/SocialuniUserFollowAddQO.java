package com.socialuni.social.follow.model.follow;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
public class SocialuniUserFollowAddQO {
    /**
     * 被关注的用户id
     */
    @NotBlank(message = "入参为空异常")
    private String beUserId;
}
