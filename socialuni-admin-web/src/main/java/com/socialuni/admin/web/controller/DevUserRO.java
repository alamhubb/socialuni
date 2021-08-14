package com.socialuni.admin.web.controller;

import com.socialuni.entity.model.DevAccountDO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
public class DevUserRO {
    private Long id;
    private String secretKey;
    private String token;
    private String username;
    private Long devNum;
    private String phoneNum;
    private String type;
    private String realName;
    private String identityNum;
    private Integer callApiCount;
    private String status;
    private Date createTime;
    private Date updateTime;

    public DevUserRO(DevAccountDO devAccountDO) {
        this.devNum = devAccountDO.getDevNum();
        String realPhoneNum = devAccountDO.getPhoneNum();
        if (StringUtils.isNotEmpty(realPhoneNum)) {
            this.phoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
            this.username = this.phoneNum;
        }
        this.secretKey = devAccountDO.getSecretKey().substring(0, 5) + "*****************";
    }
}
