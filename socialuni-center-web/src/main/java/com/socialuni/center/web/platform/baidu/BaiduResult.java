package com.socialuni.center.web.platform.baidu;

import lombok.Data;

import java.util.Map;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 16:25
 */
@Data
public class BaiduResult {
    private Integer errno;
    private String errmsg;
    private Map<String, String> data;

    public boolean hasError() {
        return this.errno != null && this.errno != 0;
    }
}
