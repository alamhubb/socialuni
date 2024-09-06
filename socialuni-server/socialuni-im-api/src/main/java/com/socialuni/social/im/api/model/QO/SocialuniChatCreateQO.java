package com.socialuni.social.im.api.model.QO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-05-26 21:19
 */
@Data
public class SocialuniChatCreateQO {
    String chatName;
    String type;
}
