package com.socialuni.social.sdk.im.notify;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-03-21 22:28
 */
@Data
public class PushValue {
    private String value;

    public PushValue(String value) {
        this.value = value;
    }
}
