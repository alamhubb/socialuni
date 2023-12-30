package com.socialuni.social.common.sdk.dao.repository;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 抽象了状态、租户、访问。
 * 所有表中都有tanceId（租户），public (权限访问标识：完全公开,仅在本应用公开,仅在本圈内公开,仅好友查看,仅指定人查看,仅自己可看)
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/8/30 15:39
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
//@EntityListeners(TanceAuditingEntityListenerConfigurable.class)
@DynamicUpdate
@DynamicInsert
@Data
public abstract class SuperInfo extends BaseInfo {
    /**
     * tanceId对象数据库的列名。
     */
    public static final String TANCE_COMMON_NAME  = "TANCE_ID";
    /**
     * tanceId对象java的字段
     */
    public static final String TANCE_FIELD_NAME  = "tanceId";
    /**
     * 公共字段列表。
     */
    public static final String[] COMMON_FIELD  = new String[]{
            "REVISION","CREATED_BY","CREATED_TIME","UPDATED_BY","UPDATED_TIME",
            "STATUS","TANCE_ID","VISIT_FLAG",
    };
    /** 正常状态 */
    public static final Integer OK_STATUS = 1;
    /** 禁用状态 */
    public static final Integer DISABLE_STATUS = 2;
    /** 删除状态 */
    public static final Integer DELETE_STATUS = 3;

    /** 状态标识 */
    @Column()
    private Integer status ;
    /** 租户ID */
    @Column(name=TANCE_COMMON_NAME)
    private Long tanceId ;
    /** 访问标识 */
    @Column()
    private Integer visitFlag ;


    /**
     * 修饰对象的状态
     * @param obj
     * @param status
     */
    public static void decorateStatus(Object obj,Integer status){
        if(obj instanceof SuperInfo){
            ((SuperInfo) obj).setStatus(status);
        }
    }
}
