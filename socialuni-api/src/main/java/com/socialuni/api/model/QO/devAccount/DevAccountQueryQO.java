package com.socialuni.api.model.QO.devAccount;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
@Data
public class DevAccountQueryQO {
    private String threeAppId;
    private String mpType;
}
