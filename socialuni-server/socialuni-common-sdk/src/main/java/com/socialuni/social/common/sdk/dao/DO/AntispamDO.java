package com.socialuni.social.common.sdk.dao.DO;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 举报信息
 */@Entity
@Table(name = "s_app_antispam")
@Data
public class AntispamDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer reportId;
    private String contentType;
    private Integer contentId;
    /**
     * 被举报的内容，用户昵称，因用户昵称可修改，所以需要记录
     */
    private String content;
    private String msg;
    private Integer code;

    private String cause;

    private String taskId;
//    private String dataId;

    private Integer action;
    private String actionName;

    private String labels;
    private String labelNames;

    private String hints;
    private String subLabels;
    private String subLabelNames;

    //可以级联保存
    // 试试不加这行能不能级联保存 , cascade = CascadeType.PERSIST
    private Date createTime;

/*    public boolean hasViolate() {
        return WyCheckConst.violateTypes.contains(action);
    }*/
}
