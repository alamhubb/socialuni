package com.socialuni.center.sdk.model.QO;

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
