package com.socialuni.social.model.model.QO.community.circle;

import java.util.List;

public class CircleCreateQO {
    //圈子数据共享，那么自身核心竞争力是什么，利益是什么，没想明白。
    private String circleName;
    private String circleDesc;

    //娱乐，学习等等，暂不支持类型
//    private String circleClassify;
    //展示哪些话题的内容
    private List<String> tagNames;
}
