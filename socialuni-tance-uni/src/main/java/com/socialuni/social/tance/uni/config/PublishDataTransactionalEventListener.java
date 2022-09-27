package com.socialuni.social.tance.uni.config;

import com.socialuni.social.common.component.SocialuniCommonRepository;
import com.socialuni.social.common.component.SocialuniPublishDataRepository;
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
public class PublishDataTransactionalEventListener {
    @Resource
    private SocialuniPublishDataRepository publishDataRepository;
    /**
     * 之后再事务提交之后也就是成功执行，才去同步到开发者服务器中。
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishDataToDev(){
        List<SocialuniPublishDataRepository.PublishDataModel> publishDataModelList = publishDataRepository.getPublishDataModelList();
        String tance_id = null;
        // 循环调用插入到开发者的接口中去。 放心顺序也是一样保持一致的。
        for (SocialuniPublishDataRepository.PublishDataModel publishDataModel : publishDataModelList) {

        }
    }
}
