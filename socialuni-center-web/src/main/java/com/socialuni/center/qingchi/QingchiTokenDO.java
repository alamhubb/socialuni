package com.socialuni.center.qingchi;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "qingchi_token",
        indexes = {
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"token"}),
        })
@Data
@NoArgsConstructor
public class QingchiTokenDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String token;
}
