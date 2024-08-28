package com.socialuni.social.common.sdk.utils;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;

import java.util.List;
import java.util.Optional;

public class SocialuniListUtil {

    public static <T extends SocialuniBaseDO> T findById(List<T> list, Integer id) {
        return list.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }
}
