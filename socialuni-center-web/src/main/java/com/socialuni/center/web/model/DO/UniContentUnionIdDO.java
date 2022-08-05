package com.socialuni.center.web.model.DO;

import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.web.sdk.utils.RequestUtil;
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
                @Index(columnList = "dataDevId"),
                @Index(columnList = "dataContentUnionId"),
                @Index(columnList = "readDevId"),
                @Index(columnList = "readContentUnionId"),
        },
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"contentType", "contentId"}),
                @UniqueConstraint(columnNames = {"dataDevId", "dataContentUnionId", "writeDevId"}),
                @UniqueConstraint(columnNames = {"writeDevId", "writeContentUnionId"}),
        }
)
@Data
@NoArgsConstructor
public class UniContentUnionIdDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, updatable = false)
    private String contentType;
    private Integer contentId;
    //数据所有方
    @Column(nullable = false, updatable = false)
    private Integer dataDevId;
    private Integer dataContentUnionId;
    //写入方
    private Integer writeDevId;
    private Integer writeContentUnionId;

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public UniContentUnionIdDO(String contentType, Integer dataDevId, Integer dataContentUnionId, Integer writeDevId, Integer writeContentUnionId, Integer contentId) {
        this.contentType = contentType;
        this.dataDevId = DevAccountUtils.getDataDevIdNotNull();
        this.dataContentUnionId = RequestUtil.getDataSocialuniId();
        this.writeDevId = writeDevId;
        this.contentId = contentId;
        this.writeContentUnionId = writeContentUnionId;
    }
}
