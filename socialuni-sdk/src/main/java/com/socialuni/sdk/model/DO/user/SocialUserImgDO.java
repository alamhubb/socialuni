package com.socialuni.sdk.model.DO.user;

import com.socialuni.sdk.model.DO.user.base.SocialUserImgBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "s_user_img",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "userId"),
                @Index(columnList = "status"),
        }
)
@Data
public class SocialUserImgDO extends SocialUserImgBaseDO implements Serializable {

}
