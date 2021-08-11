package com.socialuni.api.model.QO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-28 16:11
 */
@Data
public class CenterUserTalkQueryQO {
    @NotBlank(message = "入参为空异常")
    private String userId;
    private List<String> talkIds;
}
