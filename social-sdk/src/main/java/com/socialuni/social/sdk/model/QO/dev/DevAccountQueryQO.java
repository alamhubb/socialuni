package com.socialuni.social.sdk.model.QO.dev;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DevAccountQueryQO {
    @NotBlank
    private String secretKey;

    public DevAccountQueryQO(String secretKey) {
        this.secretKey = secretKey;
    }
}
