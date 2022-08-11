package com.socialuni.sdk.model.QO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CenterUserIdQO {
    @NotBlank
    private String userId;

    public CenterUserIdQO(String userId) {
        this.userId = userId;
    }
}