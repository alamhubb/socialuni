package com.socialuni.sdk.repository;

import com.socialuni.sdk.model.DO.message.MessageDO;
import com.socialuni.sdk.model.DO.base.BaseModelDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface MessageRepository extends JpaRepository<MessageDO, Integer> {
    BaseModelDO findOneByUnionIdAndStatus(Integer id, String status);

    List<MessageDO> findTop30ByChatIdAndStatusAndIdNotInOrderByIdDesc(Integer chatId, String msgStatus, List<Integer> ids);

    List<MessageDO> findTop31ByChatIdAndStatusAndIdNotInOrderByIdDesc(Integer chatId, String msgStatus, List<Integer> ids);


    Optional<MessageDO> findFirstOneByIdAndStatusIn(Integer id, List<String> msgStatus);

    //未登陆时查询官方提供的chat列表
//    List<MessageDO> findTop30ByChatAndChatStatusAndStatusInOrderByCreateTimeDescIdDesc(ChatDO chat, String chatStatus, List<String> msgStatus);

    //查询关键词触发次数时使用
    Page<MessageDO> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);

}