package com.socialuni.social.user.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//用户点赞记录表
@Entity
@Table(name = "s_user_hug_record",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "beUserId"),
        })
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniUserHugRecordDo extends SocialuniUserContactBaseDO {

}