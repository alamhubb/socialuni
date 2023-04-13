package com.socialuni.social.common.sdk.dao.facede;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCommonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

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
    /*public static <T> T findByExample(T example) {
        return getRepository().findByExample(example);
    }
*/
    /*public static <T> List<T> findAllByExample(T example) {
        return getRepository().findAllByExample(example);
    }*/

    public static <T> Long countByExample(T exampleObj) {
        return getRepository().countByExample(exampleObj);
    }

    /*public static <T> T find(T example) {
        Optional<T> optionalT = getRepository().findByExample(example);
        return optionalT.orElse(null);
    }*/

    public static SocialuniCommonRepository getRepository() {
        return repository;
    }

    public static EntityManager getEntityManager() {
        return repository.getEntityManager();
    }
}