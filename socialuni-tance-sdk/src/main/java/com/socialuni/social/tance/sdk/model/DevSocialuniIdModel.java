package com.socialuni.social.tance.sdk.model;


import com.socialuni.social.common.sdk.model.SocialuniCommonBaseModel;

import java.io.Serializable;

/**
 * 联盟id记录表，
 * 功能，通过联盟唯一id换取id，优化查询速度的作用，否则就要直接使用socialuniId, 速度会慢
 */

public class DevSocialuniIdModel extends SocialuniCommonBaseModel implements Serializable {
    //联盟id，唯一标识，不得小于32位，全网唯一
    private String socialuniId;
}