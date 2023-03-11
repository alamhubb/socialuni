package com.socialuni.social.sdk.utils;

import lombok.Data;

@Data
public class KeyQueryQO<T> {
    T queryData;
    Object jsonKey;
}
