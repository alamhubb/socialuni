package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.common.api.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class SocialuniCommonRepository {
    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T> T save(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t);
        t = simpleJpaRepository.save(t);
        return t;
    }

    @Transactional
    public <T> List<T> saveAll(List<T> t) {
        if (t.size() == 0) {
            return t;
        }
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t.get(0));
        t = simpleJpaRepository.saveAll(t);
        return t;
    }

    @Transactional
    public <T> void delete(T t) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(t);
        simpleJpaRepository.delete(t);
    }

    @Transactional
    public <T> void deleteById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(tClass);
        simpleJpaRepository.deleteById(id);
    }

    public <T> T findById(Integer id, Class<T> tClass) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(tClass);
        T t = simpleJpaRepository.findById(id).orElse(null);
        return t;
    }

    /*@Cacheable(cacheNames = "commonRepostoryFindByExample",key = "#exampleObj.toString()")
    public <T> T findByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findOne(example).orElse(null);
    }*/

    /*public <T> List<T> findAllByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.findAll(example);
    }*/

    public <T> Long countByExample(T exampleObj) {
        SimpleJpaRepository<T, Integer> simpleJpaRepository = getSimpleJpaRepository(exampleObj);
        Example<T> example = Example.of(exampleObj);
        return simpleJpaRepository.count(example);
    }


    public <T> T findFirstByExample(Example<T> example, CriteriaQuery<T> criteriaQuery) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }


    /**
     * 获得SimpleJpaRepository
     *
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(Class<T> tClass) {
        return new SimpleJpaRepository<>(tClass, entityManager);
    }

    /**
     * 获得SimpleJpaRepository
     *
     * @param t
     * @param <T>
     * @return
     * @see #getSimpleJpaRepository(Class)
     */
    private <T> SimpleJpaRepository<T, Integer> getSimpleJpaRepository(T t) {
        return getSimpleJpaRepository((Class<T>) t.getClass());
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }


    //使用这个的问题是缓存不会被清理
    public <T extends SocialuniUserInfoBaseDO> T findFirstByUserId(Integer userId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        criteriaQuery.where(userIdPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        List<T> list = query.setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    //使用这个的问题是缓存不会被清理
    public <T extends SocialuniBaseDO> T findFirstOrderByIdDesc(Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        List<T> list = query.setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUnionContentBaseDO> T findByUnionId(Integer unionId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("unionId"), unionId);
        criteriaQuery.where(userIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniBaseDO> T findByCustomField(String field, Integer fieldId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get(field), fieldId);
        criteriaQuery.where(userIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    /**
     * 通过userId获得对应的子类。
     *
     * @param userId
     * @param tClass
     * @param <T>
     * @return
     */
//    @Cacheable(cacheNames = CommonRedisKey.commonFindByUserIdAndBeUserId, key = "#tClass.getSimpleName()+'-'+#userId+'-'+#beUserId")
    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserId(Integer userId, Integer beUserId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniContentBaseDO> T findFirstByStatus(String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniContentBaseDO> T findByUserIdAndCustomFieldAndStatus(Integer userId, String filedName, Integer fieldId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get(filedName), fieldId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniContentBaseDO> T findByUserIdAndCustomFieldAndStatus(Integer userId, String filedName, String fieldValue, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get(filedName), fieldValue);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public <T extends SocialuniContentBaseDO> T findByCustomFieldAndStatus(String filedName, String fieldValue, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get(filedName), fieldValue);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(beUserIdPredicate, statusPredicate);

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUserContactBaseDO> Long countByUserIdAndBeUserIdAndStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> userInfo = criteriaQuery.from(tClass);

        criteriaQuery.select(criteriaBuilder.count(userInfo));

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

//        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }


    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdOrderByIdDesc(Integer userId, Integer beUserId, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T extends SocialuniUserContactBaseDO> T findByUserIdAndBeUserIdAndNotStatus(Integer userId, Integer beUserId, String status, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate beUserIdPredicate = criteriaBuilder.equal(userInfo.get("beUserId"), beUserId);
        Predicate statusPredicate = criteriaBuilder.notEqual(userInfo.get("status"), status);
        criteriaQuery.where(userIdPredicate, beUserIdPredicate, statusPredicate);

        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        List<T> list = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(1).getResultList();

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> List<T> findByAllByUserIdOrderByIdDesc(Integer userId, Class<T> tClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);

        criteriaQuery.where(userIdPredicate);
        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public <T> List<T> findByAllByUserIdAndStatusOrderByIdDesc(Integer userId, String status, Class<T> tClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);

        Predicate userIdPredicate = criteriaBuilder.equal(userInfo.get("userId"), userId);
        Predicate statusPredicate = criteriaBuilder.equal(userInfo.get("status"), status);

        criteriaQuery.where(userIdPredicate, statusPredicate);
        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        List<T> list = entityManager.createQuery(criteriaQuery).getResultList();

        return list;
    }

    public <T> List<T> findByAllByOrderByIdDesc(Class<T> tClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);


        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("id")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    public <T> List<T> findByAllByOrderByUpdateTimeDesc(Class<T> tClass) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> userInfo = criteriaQuery.from(tClass);


        criteriaQuery.orderBy(criteriaBuilder.desc(userInfo.get("updateTime")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}