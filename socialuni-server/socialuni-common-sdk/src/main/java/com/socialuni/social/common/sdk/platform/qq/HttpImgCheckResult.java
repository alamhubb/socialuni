package com.socialuni.social.common.sdk.platform.qq;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 16:25
 */
@Data
public class HttpImgCheckResult {
    private Integer errCode;
    private String errMsg;
    public boolean hasError() {
        return this.errCode != null && this.errCode != 0;
    }
}
