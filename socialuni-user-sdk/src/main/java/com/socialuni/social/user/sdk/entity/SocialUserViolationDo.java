package com.socialuni.social.user.sdk.entity;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@Entity
@Table(name = "s_user_violation",
        //查询条件索引
       /* indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
        },*/
        uniqueConstraints = {
                //每个渠道都是唯一的
                @UniqueConstraint(columnNames = "userId"),
        }
)
@NoArgsConstructor
public class SocialUserViolationDo extends SocialuniContentBaseDO {
        /**
         * 被违规的次数，默认0
         */
        private Integer userId;
        private Integer violationCount;
        /**
         * 违规原因
         */
        private String violationReason;
        /**
         * 封禁开始时间
         */
        private Date violationStartTime;

        /**
         * 封禁结束时间
         */
        private Date violationEndTime;

        public SocialUserViolationDo(Integer userId) {
                this.userId = userId;
                this.violationCount = 0;
        }
}