package com.socialuni.social.community.sdk.model.QO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-28 16:11
 */
@Data
@NoArgsConstructor
public class SocialUserTalkQueryBO {
    @NotNull(message = "入参为空异常")
    private Long userId;
    private List<Long> talkIds;

    public SocialUserTalkQueryBO(Long userId, List<Long> talkIds) {
        this.userId = userId;
        this.talkIds = talkIds;
    }
}
