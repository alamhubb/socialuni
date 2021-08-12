package com.socialuni.entity.model.DO.user.base;

import com.socialuni.entity.model.DO.CommonContentBaseDO;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class CommonUserBaseDO extends CommonContentBaseDO {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
}