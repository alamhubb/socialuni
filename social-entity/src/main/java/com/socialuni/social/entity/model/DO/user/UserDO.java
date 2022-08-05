package com.socialuni.social.entity.model.DO.user;

import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.UniContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user",
        //查询条件索引，索引列不能为空
        indexes = {
                @Index(columnList = "gender"),
                @Index(columnList = "nickname"),
                @Index(columnList = "age"),
                @Index(columnList = "type"),
                @Index(columnList = "status"),
                //通过程序保证手机号唯一，要不然没办法设置默认值，''会出现列不唯一的情况，null会影响索引
        }
)
@Data
public class UserDO extends UniContentBaseDO implements Serializable {
    private String nickname;
    private String avatar;
    private String gender;
    private String birthday;
    private Integer age;
    private String city;
    private String type;

    public UserDO() {
        super(ContentType.user);
    }
}