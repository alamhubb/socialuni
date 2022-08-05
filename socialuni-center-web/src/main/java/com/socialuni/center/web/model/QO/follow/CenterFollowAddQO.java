package com.socialuni.center.web.model.QO.follow;

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
public class CenterFollowAddQO {
    /**
     * 被关注的用户id
     */
    @NotBlank(message = "入参为空异常")
    private String beUserId;
}
