package com.socialuni.center.web.model.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "u_content_union_id",
        indexes = {
                @Index(columnList = "contentType"),
                @Index(columnList = "contentId"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentType", "contentId"}),
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"unionId"}),
        }
)
@Data
@NoArgsConstructor
public class UniContentUnionIdDO extends SocialCommonBaseDO implements Serializable {
    private String contentType;
    private Integer contentId;
    private String unionId;
    //数据所有方
    //主要是不想泄露真实id，所以用的其他id

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public UniContentUnionIdDO(String contentType, Integer contentId, String unionId) {
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.contentId = contentId;
        this.unionId = unionId;
        this.contentType = contentType;
    }
}
