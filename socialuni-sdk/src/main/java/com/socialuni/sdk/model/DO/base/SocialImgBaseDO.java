package com.socialuni.sdk.model.DO.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class SocialImgBaseDO extends CommonContentBaseDO implements Serializable {
    //此类为talk子类，只能包含基础数据类型
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer contentId;

    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    @Column(nullable = false)
    private Integer reportNum;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;

    private String content;

    public SocialImgBaseDO() {
        this.reportNum = 0;
    }
}