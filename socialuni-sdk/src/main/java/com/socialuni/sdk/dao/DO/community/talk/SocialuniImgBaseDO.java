package com.socialuni.sdk.dao.DO.community.talk;

import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;

@MappedSuperclass
@Table(
        indexes = {
                @Index(columnList = "src")
        }
)
@Data
public class SocialuniImgBaseDO extends SocialUnionContentBaseDO implements Serializable {
    @Column(nullable = false, updatable = false)
    private String src;

    @Column(nullable = false, updatable = false)
    private Double aspectRatio;

    //压缩率
    private Double quality;
    //图片大小
    private Integer size;
}
