package com.socialuni.social.im.dao;

import com.socialuni.social.im.dao.DO.message.MessageDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
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
    SocialuniUnionContentBaseDO findOneByUnionIdAndStatus(Integer id, String status);

    List<MessageDO> findTop30ByChatIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(Integer chatId, String msgStatus, Date queryTime);

    List<MessageDO> findTop30ByChatIdAndStatusAndIdNotInOrderByIdDesc(Integer chatId, String msgStatus, List<Integer> ids);

    List<MessageDO> findTop31ByChatIdAndStatusAndIdNotInOrderByIdDesc(Integer chatId, String msgStatus, List<Integer> ids);


    Optional<MessageDO> findFirstOneByIdAndStatusIn(Integer id, List<String> msgStatus);

    //未登陆时查询官方提供的chat列表
//    List<MessageDO> findTop30ByChatAndStatusAndStatusInOrderByCreateTimeDescIdDesc(ChatDO chat, String chatStatus, List<String> msgStatus);

    //查询关键词触发次数时使用
    Page<MessageDO> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);

}