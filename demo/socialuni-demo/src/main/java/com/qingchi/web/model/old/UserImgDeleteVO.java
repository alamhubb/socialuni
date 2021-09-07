package com.qingchi.web.model.old;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserImgDeleteVO {
    @NotBlank
    private String id;
}
