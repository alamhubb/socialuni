package com.socialuni.social.common.sdk.dao.repository;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 9:55
 * @since 1.0
 */
public class DelegateExtendBaseRepository<T, ID >
        extends ExtendBaseRepository<T, ID > {
    protected final EntityManager entityManager;
    protected final JpaEntityInformation entityInformation;
    private volatile PersistenceProvider provider;
    private  ExtendBaseRepository<T, ID > extendBaseRepository;

    public DelegateExtendBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
        DelegateExtendBaseFactory delegateExtendBaseFactory = SpringUtil.getBean(DelegateExtendBaseFactory.class);
        extendBaseRepository = delegateExtendBaseFactory.newExtendBaseRepository(entityInformation,entityManager);
    }

    @Override
    @Transactional
    public boolean incr(ID id, String columnName, Long count) {
        return extendBaseRepository.incr(id, columnName, count);
    }

    @Override
    public PersistenceProvider getProvider() {
        return extendBaseRepository.getProvider();
    }

    @Override
    public void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata) {
        extendBaseRepository.setRepositoryMethodMetadata(crudMethodMetadata);
    }

    @Override
    public void setEscapeCharacter(EscapeCharacter escapeCharacter) {
        extendBaseRepository.setEscapeCharacter(escapeCharacter);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        extendBaseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        extendBaseRepository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteAllById(Iterable<? extends ID> ids) {
        extendBaseRepository.deleteAllById(ids);
    }

    @Override
    @Transactional
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        extendBaseRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        extendBaseRepository.deleteAll(entities);
    }

    @Override
    @Transactional
    public void deleteAllInBatch(Iterable<T> entities) {
        extendBaseRepository.deleteAllInBatch(entities);
    }

    @Override
    @Transactional
    public void deleteAll() {
        extendBaseRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllInBatch() {
        extendBaseRepository.deleteAllInBatch();
    }

    @Override
    public Optional<T> findById(ID id) {
        return extendBaseRepository.findById(id);
    }

    @Override
    @Deprecated
    public T getOne(ID id) {
        return extendBaseRepository.getOne(id);
    }

    @Override
    @Deprecated
    public T getById(ID id) {
        return extendBaseRepository.getById(id);
    }

    @Override
    public T getReferenceById(ID id) {
        return extendBaseRepository.getReferenceById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return extendBaseRepository.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return extendBaseRepository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return extendBaseRepository.findAllById(ids);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return extendBaseRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return extendBaseRepository.findAll(pageable);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return extendBaseRepository.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return extendBaseRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return extendBaseRepository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return extendBaseRepository.findAll(spec, sort);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return extendBaseRepository.findOne(example);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return extendBaseRepository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return extendBaseRepository.exists(example);
    }

    @Override
    public boolean exists(Specification<T> spec) {
        return extendBaseRepository.exists(spec);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return extendBaseRepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return extendBaseRepository.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return extendBaseRepository.findAll(example, pageable);
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return extendBaseRepository.findBy(example, queryFunction);
    }

    @Override
    public long count() {
        return extendBaseRepository.count();
    }

    @Override
    public long count(Specification<T> spec) {
        return extendBaseRepository.count(spec);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        return extendBaseRepository.save(entity);
    }

    @Override
    @Transactional
    public <S extends T> S saveAndFlush(S entity) {
        return extendBaseRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return extendBaseRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return extendBaseRepository.saveAllAndFlush(entities);
    }

    @Override
    @Transactional
    public void flush() {
        extendBaseRepository.flush();
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<T> entities) {
        extendBaseRepository.deleteInBatch(entities);
    }
}