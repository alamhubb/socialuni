package com.socialuni.social.common.facade;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniCommonRepository;

import java.util.Optional;

public abstract class SocialuniRepositoryFacade {


    private final static SocialuniCommonRepository repository = SpringUtil.getBean(SocialuniCommonRepository.class);


    public static <T> T save(T t) {
        return getRepository().save(t);
    }



    public static <T> T findById(Integer id, Class<T> tClass) {
        return getRepository().findById(id, tClass);
    }

    /**
     * @see SocialuniCommonRepository#findByExample(Object)
     * @param example
     * @param <T>
     * @return
     */
    public static <T> Optional<T> findByExample(T example) {
        return getRepository().findByExample(example);
    }

    private static SocialuniCommonRepository getRepository() {
        return repository;
    }
}