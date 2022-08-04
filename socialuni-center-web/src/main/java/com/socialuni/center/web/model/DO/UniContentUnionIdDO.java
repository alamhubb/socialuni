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
                @UniqueConstraint(columnNames = {"contentType", "dataDevId", "contentId"}),
                @UniqueConstraint(columnNames = {"contentType", "dataDevId", "dataContentUnionId", "readDevId", "readContentUnionId"}),
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

    public UniContentUnionIdDO(Integer dataDevId, String contentType, Integer contentId) {
        this.dataDevId = dataDevId;
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public UniContentUnionIdDO(Integer dataDevId, String contentType, Integer contentId, Integer dataContentUnionId) {
        this(dataDevId, contentType, contentId);
        this.dataContentUnionId = dataContentUnionId;
    }

}
