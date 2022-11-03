package com.socialuni.social.common.sdk.component;


import java.util.Optional;

public interface SocialuniCommonRepositoryInterface {
    <T> T save(T t);

    <T> void delete(T t);

    <T> void deleteById(Integer id, Class<T> tClass);

    <T> T findById(Integer id, Class<T> tClass);

    <T> Optional<T> findByExample(T exampleObj);
}