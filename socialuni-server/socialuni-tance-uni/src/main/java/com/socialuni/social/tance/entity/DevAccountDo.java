package com.socialuni.social.tance.entity;

import com.socialuni.social.tance.sdk.enumeration.DevAccountType;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 开发者账户
 */
@Data
@Entity
@Table(name = "s_dev_account",
        indexes = {
                @Index(columnList = "realName"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"userId"}),
                @UniqueConstraint(columnNames = {"secretKey"}),
                @UniqueConstraint(columnNames = {"devNum"}),
                @UniqueConstraint(columnNames = {"phoneNum"}),
                @UniqueConstraint(columnNames = {"socialuniId"}),
        }
)
public class DevAccountDo extends DevAccountModel implements Serializable {
}