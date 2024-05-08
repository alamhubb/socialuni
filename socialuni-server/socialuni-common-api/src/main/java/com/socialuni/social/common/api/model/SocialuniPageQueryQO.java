package com.socialuni.social.common.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class SocialuniPageQueryQO<T> {
    private Boolean firstLoad;
    private Date queryTime;
    private Integer pageSize;
    private Integer pageNum;
    private T queryData;
}
