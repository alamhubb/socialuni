package com.socialuni.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CenterUserIdQO {
    @NotBlank
    private String userId;
}