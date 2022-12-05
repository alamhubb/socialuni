package com.socialuni.social.tance.repository;

import com.socialuni.social.common.sdk.component.SocialuniPublishDataComponent;
import com.socialuni.social.common.api.utils.RequestUtil;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

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
        SocialuniPublishDataComponent socialuniPublishDataComponentByRequestAttribute = getSocialuniPublishDataComponentByRequestAttribute();
        if(socialuniPublishDataComponentByRequestAttribute != null){
            // 代表新增还是修改
            if (this.isNew(entity)) {
                socialuniPublishDataComponentByRequestAttribute.insert(entity);
            }else{
                socialuniPublishDataComponentByRequestAttribute.update(entity);
            }
        }
        return save;
    }

    /**
     * 消费，调用SocialuniPublishDataComponent方法，添加数据。
     * @see SocialuniPublishDataComponent
     * @param consumer
     */
    public static void acceptPublishDataComponent(Consumer<SocialuniPublishDataComponent> consumer){
        SocialuniPublishDataComponent publishDataComponent =
                getSocialuniPublishDataComponentByRequestAttribute();
        // 执行
        if(publishDataComponent != null && consumer != null) {
            consumer.accept(publishDataComponent);
        }
    }

    /**
     * 从RequestAttribute中获得{@code SocialuniPublishDataComponent}
     * @return
     */
    public static SocialuniPublishDataComponent getSocialuniPublishDataComponentByRequestAttribute(){
        SocialuniPublishDataComponent publishDataComponent = null;
        HttpServletRequest request = RequestUtil.getRequest();
        if(request == null) return publishDataComponent;
        Object attribute = request.getAttribute(SocialuniPublishDataComponent.UUID_NAME);
        if(attribute == null) return publishDataComponent;
        publishDataComponent = (SocialuniPublishDataComponent) attribute;
        return publishDataComponent;
    }
    /**
     * 初始化{@code SocialuniPublishDataComponent} 放入到request的Attribute中。
     * @param request
     */
    public static void publishDataInitialized(HttpServletRequest request) {
        if(request == null) return;
        Object attribute = request.getAttribute(SocialuniPublishDataComponent.UUID_NAME);
        if(attribute == null) {
            request.setAttribute(SocialuniPublishDataComponent.UUID_NAME,new SocialuniPublishDataComponent());
        }
    }
}
