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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SocialPhoneNumAuthCodeQO extends SocialPhoneNumQO{
    //    private String phoneCountryCode;
    @NotBlank
    @Length(min = 4, max = 4)
    private String authCode;

    public SocialPhoneNumAuthCodeQO(String phoneNum, String authCode) {
        super(phoneNum);
        this.authCode = authCode;
    }
}
