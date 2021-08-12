package com.socialuni.entity.model.DO.base;


import java.util.Date;

public interface BaseModelDO {
    Integer getId();

    String getStatus();

    void setStatus(String status);

    String getDeleteReason();

    void setDeleteReason(String deleteReason);

    Integer getUserId();

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

    String getContent();

    Date getCreateTime();

    Integer getReportNum();

    void setReportNum(Integer reportNum);

    String getReportContentType();
}
