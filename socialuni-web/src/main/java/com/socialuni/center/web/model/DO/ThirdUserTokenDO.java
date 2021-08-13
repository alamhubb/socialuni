package com.socialuni.center.web.model.DO;

import com.socialuni.entity.model.DO.user.base.CommonTokenBaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "third_user_token",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "devId"),
        }
)
@Data
public class ThirdUserTokenDO extends CommonTokenBaseDO {
    @Column(nullable = false)
    private Integer devId;

    public ThirdUserTokenDO() {
    }

    public ThirdUserTokenDO(Integer userId, String token, Integer devId) {
        super(userId, token);
        this.devId = devId;
    }
}