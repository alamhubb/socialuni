package com.socialuni.social.tance.repository;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniPublishDataComponent;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 要求所有的增删改查都使用该类，才能实现。
 * 里面使用基类就能实现啦。
 * @author wulinghui
 * @version 1.0
 * @module tance.uni
 * @date 2022/9/28 15:07
 * @see SocialuniPublishDataComponent
 * @since 1.0
 */
public class PublishDataTanceBaseRepository <T, ID >
        extends TanceBaseRepository<T, ID> {
    public PublishDataTanceBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public void delete(T entity) {
        super.delete(entity);
        acceptPublishDataComponent( (consumer) -> consumer.delete(entity));
    }

    @Override
    public <S extends T> S save(S entity) {
        S save = super.save(entity);
        acceptPublishDataComponent( (consumer) -> consumer.save(save));
        return save;
    }

    /**
     * 消费，调用SocialuniPublishDataComponent方法，添加数据。
     * @see SocialuniPublishDataComponent
     * @param consumer
     */
    protected void acceptPublishDataComponent(Consumer<SocialuniPublishDataComponent> consumer){
        SocialuniPublishDataComponent publishDataComponent = SpringUtil.getBean(SocialuniPublishDataComponent.class);
        if(publishDataComponent != null) {
            consumer.accept(publishDataComponent);
        }
    }
}
