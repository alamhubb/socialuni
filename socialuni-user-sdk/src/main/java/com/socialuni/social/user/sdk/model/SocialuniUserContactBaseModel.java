package com.socialuni.social.user.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@NoArgsConstructor
@MappedSuperclass
public class SocialuniUserContactBaseModel extends SocialuniUserInfoBaseModel implements Serializable {
    private Integer beUserId;
}