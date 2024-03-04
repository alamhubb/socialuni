package com.socialuni.social.community.sdk.model.QO.circle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CircleTalkTabInfoQO {
    //圈子数据共享，那么自身核心竞争力是什么，利益是什么，没想明白。
    @NotBlank
    private String circleName;
}
