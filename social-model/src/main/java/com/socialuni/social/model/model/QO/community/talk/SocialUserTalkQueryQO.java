package com.socialuni.social.model.model.QO.community.talk;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SocialUserTalkQueryQO {
    @NotNull(message = "入参为空异常")
    private String userId;
    private List<Integer> talkIds;
}
