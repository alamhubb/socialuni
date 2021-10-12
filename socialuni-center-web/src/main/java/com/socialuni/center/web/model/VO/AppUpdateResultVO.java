package com.socialuni.center.web.model.VO;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class AppUpdateResultVO {
    //更新方式，热更新，还是reinstall
    private String updateType;
    //用来判断，首页、同城、关注
    private String updateUrl;
    private String updateHint;

    public AppUpdateResultVO(String updateType) {
        this.updateType = updateType;
    }

    public AppUpdateResultVO(String updateType, String updateUrl) {
        this.updateType = updateType;
        this.updateUrl = updateUrl;
    }

    public AppUpdateResultVO(String updateType, String updateUrl, String updateHint) {
        this.updateType = updateType;
        this.updateUrl = updateUrl;
        this.updateHint = updateHint;
    }
}
