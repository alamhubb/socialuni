package com.socialuni.social.sdk.utils;

import java.util.List;
import java.util.stream.Collectors;

public class SocialIdUtil {
    /**
     * 将stringid列表转为int
     *
     * @param stringIds
     * @return
     */
    public static List<Integer> stringIdsToInt(List<String> stringIds) {
        return stringIds.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * 将int id列表转为 stirng id
     *
     * @param intIds
     * @return
     */
    public static List<String> intIdsToStr(List<Integer> intIds) {
        return intIds.stream().map(Object::toString).collect(Collectors.toList());
    }
}
