package com.socialuni.social.user.sdk.model;

import com.socialuni.social.common.sdk.model.SocialuniContentBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录相关，只有登录时才用得到的表
 * 自己表示字段，其他表示关联的表内字段
 */
@Data
@NoArgsConstructor
public class SocialUserViolationModel extends SocialuniContentBaseModel implements Serializable {
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

        public SocialUserViolationModel(Integer userId) {
                this.userId = userId;
                this.violationCount = 0;
        }
}