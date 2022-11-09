package com.socialuni.social.tance.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;
import com.socialuni.social.common.api.enumeration.PublishDataCacheable;
import com.socialuni.social.common.api.model.PublishDataModel;
import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 把数据同步到开发者的监听入口。
 *
 * @author wulinghui
 * @version 1.0
 * @module tance.uni
 * @date 2022/9/27 15:54
 * @see <a href='https://blog.csdn.net/f641385712/article/details/91897175'>Spring事务监听机制</a>
 * @see <a href='http://www.wjhsh.net/zhimingxin-p-8182623.html'>Spring启动时获取自定义注解的属性值</a>
 * @since 1.0
 */
@Component
@Slf4j
public class PublishDataTransactionalEventListener implements BeanPostProcessor {
    private static final Map<Class, Set<String>> CLASS_SET_MAP = new HashMap();

    @Resource
    private CacheManager cacheManager;

    /**
     * 之后再事务提交之后也就是成功执行，才去同步到开发者服务器中。
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishDataToDev(ApplicationEvent applicationEvent) {
        // 循环调用插入到开发者的接口中去。 放心顺序也是一样保持一致的。
//        for (PublishDataModel publishDataModel : publishDataModelList) {
//
//        }

        PublishDataTanceBaseRepository.acceptPublishDataComponent((consumer) -> {
            List<PublishDataModel> publishDataModelList = consumer.getPublishDataModelList();
            log.debug("插入到开发者的接口中={}", publishDataModelList);

            // 同步redis缓存。
            for (PublishDataModel publishDataModel : publishDataModelList) {
                Set<String> cacheKeySet = getNamesByClass(publishDataModel.getData().getClass());
                // 获得所有的key内容。
                if (cacheKeySet != null) {
                    for (String cacheName : cacheKeySet) {
                        Cache cache = cacheManager.getCache(cacheName);
                        // 清除所有的缓存。
                        cache.clear();
                    }
                }
            }
        });
    }

    /**
     * 收集所有的自定义类型注解和模型。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                /**
                 * AnnotationUtils 解决注解别名，包括显式别名、隐式别名、传递的隐式别名，还可以查的指定注解的属性信息。
                 * AnnotationUtils 底层使用动态代理的方式处理注解别名的问题。
                 */
                PublishDataCacheable cacheEvict = AnnotationUtils.findAnnotation(method, PublishDataCacheable.class);
                if (null != cacheEvict) {
                    // 合并modelClass
                    Class[] modelClass = cacheEvict.modelClass();
                    HashSet<Class> mergeModelClassSet = CollectionUtil.set(false, modelClass);
                    String[] modelClassName = cacheEvict.modelClassName();
                    for (int i = 0; i < modelClassName.length; i++) {
                        mergeModelClassSet.add(ClassUtil.loadClass(modelClassName[i], false));
                    }
                    String errMsg = String.format("在beanName=[%s]中的method=[%s],", beanName, method);
                    if (mergeModelClassSet.size() == 0) {
                        throw new RuntimeException(errMsg + "没必要使用该注解");
                    }
                    //
                    this.init(cacheEvict.value(), mergeModelClassSet, errMsg);
                }
            }
        }
        return bean;
    }


    private void init(String[] name, Collection<Class> models, String errMsg) {
        HashSet<String> newName = CollectionUtil.set(false, name);
        for (Class model : models) {
            Set<String> strings = CLASS_SET_MAP.get(model);
            if (strings != null) {
                strings = newName;
                CLASS_SET_MAP.put(model, strings);
                if (!strings.addAll(newName)) {
                    throw new RuntimeException(errMsg + "放入的cacheNames重复,请核查代码");
                }
            }
        }
    }

    /**
     * 获得通过模型所有对应的redis存储的name名称。
     *
     * @param cla
     * @return
     */
    public static Set<String> getNamesByClass(Class cla) {
        Set<String> strings = CLASS_SET_MAP.get(cla);
        return new HashSet<>(strings);
    }
}

