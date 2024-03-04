package com.socialuni.social.common.sdk.utils;

import java.util.HashMap;
import java.util.List;

public interface SocialuniCommonQueryRegistry<QueryData> {

    //根据查询中是否包含注册的key决定是否加载registryKeyResults方法，如果不这么写会存在所有都加载有性能问题
    List<String> registryKeys();

    void checked(QueryData queryQO);

    //需要实现的方法，registryKey
    HashMap<String, Object> registryKeyResults(QueryData queryQO);
}
