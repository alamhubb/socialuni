package com.socialuni.social.entity.model.DO.user.base;

import com.socialuni.social.entity.model.DO.CommonContentBaseDO;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class CommonUserBaseDO extends CommonContentBaseDO implements Serializable {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
}