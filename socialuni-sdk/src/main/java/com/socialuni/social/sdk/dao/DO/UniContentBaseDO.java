package com.socialuni.social.sdk.dao.DO;

import com.socialuni.social.common.sdk.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public class UniContentBaseDO extends SocialuniContentBaseDO implements Serializable {
    private String contentType;

    public UniContentBaseDO(String contentType) {
        this.contentType = contentType;
    }
}