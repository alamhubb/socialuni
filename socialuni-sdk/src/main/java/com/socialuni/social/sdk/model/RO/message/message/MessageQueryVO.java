package com.socialuni.social.sdk.model.RO.message.message;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
public class MessageQueryVO {
    private Integer chatId;
    @NotNull
    private List<String> msgIds;
}
