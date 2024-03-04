package com.socialuni.social.common.sdk.utils;

import lombok.Data;

@Data
public class KeyQueryQO<T> {
    T queryData;
    Object keyJson;
}
