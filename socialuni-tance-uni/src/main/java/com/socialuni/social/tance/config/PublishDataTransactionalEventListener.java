package com.socialuni.social.tance.config;

import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniPublishDataComponent;
import com.socialuni.social.common.model.PublishDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * 把数据同步到开发者的监听入口。
 * @see <a href='https://blog.csdn.net/f641385712/article/details/91897175'>Spring事务监听机制</a>
 * @author wulinghui
 * @version 1.0
 * @module tance.uni
 * @date 2022/9/27 15:54
 * @since 1.0
 */
@Component
@Slf4j
public class PublishDataTransactionalEventListener {
    /**
     * 之后再事务提交之后也就是成功执行，才去同步到开发者服务器中。
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishDataToDev(ApplicationEvent applicationEvent){
        SocialuniPublishDataComponent publishDataComponent = SpringUtil.getBean(SocialuniPublishDataComponent.class);
        List<PublishDataModel> publishDataModelList = publishDataComponent.getPublishDataModelList();
        String tance_id = null;
        // 循环调用插入到开发者的接口中去。 放心顺序也是一样保持一致的。
//        for (PublishDataModel publishDataModel : publishDataModelList) {
//
//        }
        log.debug("插入到开发者的接口中={}",publishDataModelList);
    }
}
