package com.socialuni.social.tance.dev.entity;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_dev_content_union_id",
        indexes = {
                @Index(columnList = "contentType"),
        },
        uniqueConstraints = {
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"uuid"}),
                @UniqueConstraint(columnNames = {"unionId"}),
        }
)
@Data
@NoArgsConstructor
public class SocialuniUnionIdDo extends SocialuniBaseDO implements Serializable {
    //为什么使用id，方便，可以拆分为id+类型
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private String uuid;
    //主要是不想泄露真实id，所以用的其他id
    @Column
    private Long unionId;
    //数据所属权
//    private Integer belongDevId;
    //数据来源方
    @Column(nullable = false)
    private Integer fromDevId;
    //数据所有方
    //主要是不想泄露真实id，所以用的其他id

    public long getUnionId() {
        return this.unionId;
    }


    @Deprecated
    public String getUuid() {
        return uuid;
    }

    public Long getSelfSysId() {
        return Long.valueOf(this.getId());
    }

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public SocialuniUnionIdDo(String contentType, Long unionId, Integer fromDevId) {
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.contentType = contentType;
        this.unionId = unionId;
        this.uuid = String.valueOf(unionId);
//        this.belongDevId = belongDevId;
        this.fromDevId = fromDevId;
    }
}


