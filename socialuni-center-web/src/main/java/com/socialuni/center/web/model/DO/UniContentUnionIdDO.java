package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.model.SocialContentIdDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "u_content_union_id",
        indexes = {
                @Index(columnList = "id"),
                @Index(columnList = "contentType"),
                @Index(columnList = "contentId"),
                @Index(columnList = "originalDevId"),
                @Index(columnList = "originalContentUnionId"),
                @Index(columnList = "fromDevId"),
                @Index(columnList = "fromContentUnionId"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentType", "contentId"}),
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"originalDevId", "originalContentUnionId"}),
                @UniqueConstraint(columnNames = {"fromDevId", "fromContentUnionId"}),
        }
)
@Data
@NoArgsConstructor
public class UniContentUnionIdDO extends SocialContentIdDO implements Serializable {
    private Integer contentId;
    //数据所有方
    @Column(nullable = false, updatable = false)
    private Integer originalDevId;
    //主要是不想泄露真实id，所以用的其他id
    private Integer originalContentUnionId;
    //写入方
    private Integer fromDevId;
    private Integer fromContentUnionId;

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public UniContentUnionIdDO(String contentType, Integer originalDevId, Integer originalContentUnionId, Integer fromDevId, Integer fromContentUnionId, Integer contentId) {
        super(contentType);
        this.originalDevId = originalDevId;
        this.originalContentUnionId = originalContentUnionId;
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.fromDevId = fromDevId;
        this.fromContentUnionId = fromContentUnionId;
        this.contentId = contentId;
    }
}
