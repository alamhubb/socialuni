package com.socialuni.social.common.sdk.utils;

import com.qingchi.qing.jpa.dao.model.QingJpaBaseDO;

import java.util.List;

public class SocialuniListUtil {

    public static <T extends QingJpaBaseDO> T findById(List<T> list, Integer id) {
        return list.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }
}
