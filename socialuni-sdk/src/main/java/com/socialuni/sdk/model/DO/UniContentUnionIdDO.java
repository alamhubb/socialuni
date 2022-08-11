package com.socialuni.sdk.model.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "u_content_union_id",
        indexes = {
                @Index(columnList = "contentType"),
        },
        uniqueConstraints = {
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"uuid"}),
        }
)
@Data
@NoArgsConstructor
public class UniContentUnionIdDO extends SocialCommonBaseDO implements Serializable {
    private String contentType;
    private Integer contentId;
    private String uuid;
    //数据所属权
//    private Integer belongDevId;
    //数据来源方
    private Integer fromDevId;
    //数据所有方
    //主要是不想泄露真实id，所以用的其他id

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public UniContentUnionIdDO(String contentType, String uuid, Integer fromDevId) {
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.uuid = uuid;
        this.contentType = contentType;
//        this.belongDevId = belongDevId;
        this.fromDevId = fromDevId;
    }
}
