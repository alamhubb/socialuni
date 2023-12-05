package com.socialuni.social.user.sdk.model.QO;

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
public class SocialPhoneAuthCodePasswordQO extends SocialPhoneNumAuthCodeQO {

    @NotBlank
    @Length(min = 8)
    private String password;

    public SocialPhoneAuthCodePasswordQO(String phoneNum, String authCode, String password) {
        super(phoneNum, authCode);
        this.password = password;
    }
}
