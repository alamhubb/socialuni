package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.dao.DO.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class SocialuniImgBaseModel extends SocialUnionContentBaseDO implements Serializable {
    private String src;

    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;
}
