package com.socialuni.social.tance.dev.dao.DO;

import com.socialuni.social.tance.dev.model.DevAccountModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class DevAccountDo extends DevAccountModel implements Serializable {
}