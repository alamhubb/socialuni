package com.socialuni.sdk.dao.utils.common;

import com.socialuni.sdk.dao.repository.common.SocialuniCommonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniCommonDOUtil {
    static SocialuniCommonRepository repository;

    @Resource
    public void setRepository(SocialuniCommonRepository repository) {
        SocialuniCommonDOUtil.repository = repository;
    }

    public static <T> T save(T t) {
        return repository.save(t);
    }

    public static <T> T findById(Integer id, Class<T> tClass) {
        return repository.findById(id, tClass);
    }
}