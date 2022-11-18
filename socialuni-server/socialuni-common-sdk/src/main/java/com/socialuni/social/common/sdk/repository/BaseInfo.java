package com.socialuni.social.common.sdk.repository;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/8/30 15:39
 * @since 1.0
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@DynamicUpdate
@DynamicInsert
public abstract class BaseInfo implements Serializable,Cloneable{
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    /** 乐观锁 */
    @Column()
    private Double revision ;
    /** 创建人 */
    @Column()
    @CreatedBy
    private String createdBy ;
    /** 创建时间 */
    @Column()
    @CreatedDate
    private Date createdTime ;
    /** 更新人 */
    @Column()
    @LastModifiedBy
    private String updatedBy ;
    /** 更新时间 */
    @Column()
    @LastModifiedDate
    private Date updatedTime ;
}
