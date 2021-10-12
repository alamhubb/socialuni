package com.socialuni.center.web.model.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class AppUpdateVO {
    //app当前版本
    @NotNull
    private Integer version;
}
