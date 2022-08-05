package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.model.DO.base.CommonContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public class UniContentBaseDO extends CommonContentBaseDO implements Serializable {
    private String contentType;

    public UniContentBaseDO(String contentType) {
        this.contentType = contentType;
    }
}