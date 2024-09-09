package com.socialuni.social.common.api.entity;

import com.qingchi.qing.jpa.QingJpaBaseDO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class SocialuniBaseDO extends QingJpaBaseDO implements Serializable {
}