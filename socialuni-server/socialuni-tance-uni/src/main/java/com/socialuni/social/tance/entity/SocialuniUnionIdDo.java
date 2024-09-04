package com.socialuni.social.tance.entity;

import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
public class SocialuniUnionIdDo extends SocialuniUnionIdModler {
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private String uuid;
    @Column
    private Long unionId;
    //数据所属权
//    private Integer belongDevId;
    //数据来源方
    @Column(nullable = false)
    private Integer fromDevId;
    //数据所有方
    //主要是不想泄露真实id，所以用的其他id
}
