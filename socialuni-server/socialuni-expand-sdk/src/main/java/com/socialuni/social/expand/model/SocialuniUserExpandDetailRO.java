package com.socialuni.social.expand.model;

import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class SocialuniUserExpandDetailRO {
    private String schoolName;
    private String contactInfo;
    //为他人详情是，则意义为是否已经拥有查看用户联系方式的权限
    private Boolean openContactInfo;
}
