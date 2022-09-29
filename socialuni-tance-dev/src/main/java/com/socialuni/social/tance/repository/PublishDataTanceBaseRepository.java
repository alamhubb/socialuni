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
 * @deprecated 相同的名字，不能和sdk解耦。所以采用委托模式去做实现。
 * @see ExtendBaseFactory
 */
@Deprecated
public class PublishDataTanceBaseRepository<T, ID >
        extends ExtendBaseRepository<T, ID> {
    public PublishDataTanceBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
