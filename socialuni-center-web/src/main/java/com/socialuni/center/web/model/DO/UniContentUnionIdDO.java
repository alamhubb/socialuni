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
                @UniqueConstraint(columnNames = {"dataDevId", "dataContentUnionId"}),
                @UniqueConstraint(columnNames = {"readDevId", "readContentUnionId"}),
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
    //数据读取方
    private Integer readDevId;
    private Integer readContentUnionId;
    //写入方
    private Integer writeDevId;

    public UniContentUnionIdDO(Integer dataDevId, Integer writeDevId, String contentType, Integer contentId) {
        this.dataDevId = dataDevId;
        this.writeDevId = writeDevId;
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public UniContentUnionIdDO(String contentType, Integer dataDevId, Integer dataContentUnionId, Integer writeDevId, Integer contentId) {
        this(dataDevId, writeDevId, contentType, contentId);
        this.dataContentUnionId = dataContentUnionId;
    }

}
