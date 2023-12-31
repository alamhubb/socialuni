package com.socialuni.social.common.sdk.dao.DO;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "s_app_operate_record", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "beUserId"),
        @Index(columnList = "type"),
        @Index(columnList = "contentType"),
        @Index(columnList = "contentId"),
})
public class SocialuniAppOperateRecordDO extends SocialuniUserContactBaseDO implements Serializable {
    //操作主题， 群组， 动态等，
    String contentType;
    Integer contentId;
    //角色类型，系统管理员，
    String roleType;
    String roleId;
    String remark;
}

