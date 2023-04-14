package com.socialuni.social.im.dao.repository;

import com.socialuni.social.im.dao.DO.message.MessageReceiveDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * TODO〈一句话功能简述〉
 * TODO〈功能详细描述〉
 *
 * @author qinkaiyuan
 * @since TODO[起始版本号]
 */
public interface MessageReceiveRepository extends JpaRepository<MessageReceiveDO, Integer> {

    //chatId下所有未读变为已读，查询出来更改
//    List<MessageReceiveDO> findByChatUserIdAndMessageStatusInAndStatusAndIsReadFalseAndIdInOrderByCreateTimeDescIdDesc(Integer chatUserId, List<String> msgStatus, String status, List<Long> ids);
//    List<MessageReceiveDO> findByChatUserIdAndStatusAndIsReadFalse(Integer chatUserId, String chatUserStatus, String status);
//
//    //查询消息列表，根据chatUserId、msgReceiveStatus、msgIds 按照msgReceiveStatus 倒序排序
//    //调用这两个之前必须先判断 chat 为enable
    List<MessageReceiveDO> findTop30ByChatUserIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(Integer chatUserId, String status, Date createTime);

}