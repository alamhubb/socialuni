package com.socialuni.social.report.sdk.dao.DO;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-12-22 18:46
 */
@Data
@Entity
/**
 * 违规词
 */
@Table(name = "s_white_keywords", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"word"})
})
public class WhiteKeywordsDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String word;
    private String cause;
    @Column(columnDefinition = "varchar(255) default '初始'")
    private String status;
    //触发次数
    @Column(columnDefinition = "int default 0")
    private Integer triggerCount;
    private Date createTime;
    private Date updateTime;
}
