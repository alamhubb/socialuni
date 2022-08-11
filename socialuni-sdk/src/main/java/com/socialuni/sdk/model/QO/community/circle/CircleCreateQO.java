package com.socialuni.sdk.model.QO.community.circle;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CircleCreateQO {
    //圈子数据共享，那么自身核心竞争力是什么，利益是什么，没想明白。
    @NotBlank
    private String circleName;
    private String circleDesc;
    @NotBlank
    private Integer tagTypeId;
    //圈子的头像，先不支持
//    private String circleAvatar;

    //娱乐，学习等等，暂不支持类型
//    private String circleClassify;
    //展示哪些话题的内容, 先不支持白名单，黑名单
//    private List<String> tagNames;
    //不展示的话题
//    private List<String> blackTagNames;
}
