package com.socialuni.social.common.sdk.facade;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.sdk.component.SocialuniCommonRepository;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
public abstract class SocialuniRepositoryFacade {

    private final static SocialuniCommonRepository repository = SpringUtil.getBean(SocialuniCommonRepository.class);


    public static <T> T save(T t) {
        return getRepository().save(t);
    }


    public static <T> T findById(Integer id, Class<T> tClass) {
        return getRepository().findById(id, tClass);
    }

    /**
     * @param example
     * @param <T>
     * @return
     * @see SocialuniCommonRepositoryInterface#findByExample(Object)
     */
    public static <T> T findByExample(T example) {
        return getRepository().findByExample(example);
    }

    /*public static <T> T find(T example) {
        Optional<T> optionalT = getRepository().findByExample(example);
        return optionalT.orElse(null);
    }*/

    private static SocialuniCommonRepository getRepository() {
        return repository;
    }

    public static EntityManager getEntityManager() {
        return repository.getEntityManager();
    }
}