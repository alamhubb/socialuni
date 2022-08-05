package com.socialuni.social.entity.model.DO;

import com.socialuni.social.entity.model.DO.base.CommonContentBaseDO;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class SocialuniContentBaseDO extends CommonContentBaseDO implements Serializable {
    String contentType;

    public SocialuniContentBaseDO(String contentType) {
        this.contentType = contentType;
    }
}