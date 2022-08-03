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
                @UniqueConstraint(columnNames = {"readDevId", "readContentUnionId"}),
                @UniqueConstraint(columnNames = {"dataDevId", "dataContentUnionId"}),
        }
)
@Data
@NoArgsConstructor
public class UniContentUnionIdDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String contentType;
    @Column(nullable = false, updatable = false)
    private Integer contentId;
    //数据所有方
    @Column(nullable = false, updatable = false)
    private Integer dataDevId;
    @Column(nullable = false, updatable = false)
    private String dataContentUnionId;
    //数据读取方
    @Column(nullable = false, updatable = false)
    private String readDevId;
    @Column(nullable = false, updatable = false)
    private String readContentUnionId;

    public UniContentUnionIdDO(String contentType, Integer contentId) {
        this.contentId = contentId;
        this.contentType = contentType;
    }
}
