package com.socialuni.social.im.api.model.QO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
public class MessageQueryVO {
    private String  chatId;
    private Date queryTime;
}
