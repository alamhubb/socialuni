package com.socialuni.social.tance.config;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.socialuni.social.common.component.SocialuniPublishDataComponent;
import com.socialuni.social.common.model.PublishDataModel;
import com.socialuni.social.common.utils.RequestUtil;
import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        // 循环调用插入到开发者的接口中去。 放心顺序也是一样保持一致的。
//        for (PublishDataModel publishDataModel : publishDataModelList) {
//
//        }

        PublishDataTanceBaseRepository.acceptPublishDataComponent( (consumer) -> {
            List<PublishDataModel> publishDataModelList = consumer.getPublishDataModelList();
            log.debug("插入到开发者的接口中={}", publishDataModelList);
        });
    }
}
