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
public class SocialPhoneNumQO {
    @NotBlank
    @Length(min = 11, max = 11)
    private String phoneNum;

    public SocialPhoneNumQO(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
