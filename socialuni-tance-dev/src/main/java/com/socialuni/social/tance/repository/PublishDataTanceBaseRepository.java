package com.socialuni.social.tance.repository;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniPublishDataComponent;
import com.socialuni.social.common.repository.ExtendBaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;

/**
 * 空的实现。仅仅用于编译不需要修改代码的使用。
 * @author wulinghui
 * @version 1.0
 * @module tance.uni
 * @date 2022/9/28 15:07
 * @since 1.0
 */
public class PublishDataTanceBaseRepository<T, ID >
        extends ExtendBaseRepository<T, ID> {
    public PublishDataTanceBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
