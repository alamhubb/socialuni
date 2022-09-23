package com.socialuni.social.sdk.model.RO.user.phone;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author qinkaiyuan
 * @date 2019-02-14 22:03
 */
@Data
@NoArgsConstructor
public class SocialSendAuthCodeQO {
    @NotBlank
    @Length(min = 11, max = 11)
    private String phoneNum;

    public SocialSendAuthCodeQO(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
