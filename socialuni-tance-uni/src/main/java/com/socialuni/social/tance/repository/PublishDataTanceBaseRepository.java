package com.socialuni.social.tance.repository;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniPublishDataComponent;
import com.socialuni.social.common.utils.RequestUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
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
    public static void acceptPublishDataComponent(Consumer<SocialuniPublishDataComponent> consumer){
        HttpServletRequest request = RequestUtil.getRequest();
        if(request == null) return;
        Object attribute = request.getAttribute(SocialuniPublishDataComponent.UUID_NAME);
        if(attribute == null) return;
        SocialuniPublishDataComponent publishDataComponent = (SocialuniPublishDataComponent) attribute;
        if(publishDataComponent != null) {
            consumer.accept(publishDataComponent);
        }
    }

    public static void publishDataInitialized(HttpServletRequest request) {
        if(request == null) return;
        Object attribute = request.getAttribute(SocialuniPublishDataComponent.UUID_NAME);
        if(attribute == null) {
            request.setAttribute(SocialuniPublishDataComponent.UUID_NAME,new SocialuniPublishDataComponent());
        }
    }
}
