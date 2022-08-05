package com.socialuni.center.web.model.DO;

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

    public UniContentUnionIdDO(String contentType, Integer dataDevId, Integer dataContentUnionId, Integer writeDevId, Integer contentId) {
        this.contentType = contentType;
        this.dataDevId = dataDevId;
        this.dataContentUnionId = dataContentUnionId;
        this.writeDevId = writeDevId;
        this.contentId = contentId;
    }

    public UniContentUnionIdDO(Integer dataDevId, Integer dataContentUnionId, Integer writeDevId, Integer writeContentUnionId, String contentType) {
        this(contentType, dataDevId, dataContentUnionId, writeDevId, null);
        this.writeContentUnionId = writeContentUnionId;
    }

}
