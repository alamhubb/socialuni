package com.socialuni.api.model.QO.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CenterUserIdQO {
    @NotBlank
    private String userId;
}