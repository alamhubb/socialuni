package com.socialuni.social.common.sdk.model.QO;

import lombok.Data;

@Data
public class KeyQueryQO<T> {
    T queryData;
    Object keyJson;
}
