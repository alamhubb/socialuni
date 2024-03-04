package com.socialuni.social.common.sdk.facade;

import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/27 9:54
 * @since 1.0
 */
public abstract class SocialuniPageQueryFacede {

    public static <T, Q, R> List<T> pageQuery(List<T> data, Q queryData, R queryService, Integer pageNum) {


        /*if (data.size() < pageNum) {
            queryService.
        }*/
        return null;
    }
}
