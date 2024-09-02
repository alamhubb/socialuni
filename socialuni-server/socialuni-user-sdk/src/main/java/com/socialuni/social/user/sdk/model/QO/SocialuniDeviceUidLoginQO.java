package com.socialuni.social.user.sdk.model.QO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
public class SocialuniDeviceUidLoginQO {

    @NotBlank
    private String deviceUid;
}
