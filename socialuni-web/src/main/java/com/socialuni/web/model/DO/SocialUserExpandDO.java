package com.socialuni.web.model.DO;

import com.socialuni.sdk.model.DO.SocialCommonBaseDO;
import com.socialuni.sdk.model.DO.user.SocialUnionContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.lang.annotation.Documented;

//用户扩展类
@Entity
@Table(name = "s_user_expand")
@Data
@NoArgsConstructor
public class SocialUserExpandDO extends SocialCommonBaseDO implements Serializable {
    private String school;
}