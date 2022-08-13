package com.socialuni.sdk.model.DO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "s_user",
        //查询条件索引，索引列不能为空
        indexes = {
                @Index(columnList = "gender"),
                @Index(columnList = "nickname"),
                @Index(columnList = "age"),
                @Index(columnList = "type"),
                @Index(columnList = "status"),
                //通过程序保证手机号唯一，要不然没办法设置默认值，''会出现列不唯一的情况，null会影响索引
        },
        //手机号唯一
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unionId"),
        }
)
@Data
@NoArgsConstructor
public class SocialUserDO extends SocialUnionContentBaseDO implements Serializable {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;
}