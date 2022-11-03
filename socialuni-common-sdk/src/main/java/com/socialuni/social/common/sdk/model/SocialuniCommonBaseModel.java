package com.socialuni.social.common.sdk.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialuniCommonBaseModel implements Serializable {
    private Integer id;
    private Date createTime;
}