package com.socialuni.social.sdk.im.dao.repository;

import com.socialuni.social.sdk.im.dao.DO.message.SocialuniMessageDO;
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
public interface SocialuniMessageRepository extends JpaRepository<SocialuniMessageDO, Integer> {
    SocialuniUnionContentBaseDO findOneByUnionIdAndStatus(Integer id, String status);

    List<SocialuniMessageDO> findTop30ByChatIdAndStatusAndCreateTimeLessThanOrderByCreateTimeDesc(Long chatId, String msgStatus, Date queryTime);

    List<SocialuniMessageDO> findTop30ByChatIdAndStatusAndIdNotInOrderByIdDesc(Long chatId, String msgStatus, List<Integer> ids);

    List<SocialuniMessageDO> findTop31ByChatIdAndStatusAndIdNotInOrderByIdDesc(Long chatId, String msgStatus, List<Integer> ids);


    Optional<SocialuniMessageDO> findFirstOneByIdAndStatusIn(Integer id, List<String> msgStatus);

    //未登陆时查询官方提供的chat列表
//    List<MessageDO> findTop30ByChatAndStatusAndStatusInOrderByCreateTimeDescIdDesc(ChatDO chat, String chatStatus, List<String> msgStatus);

    //查询关键词触发次数时使用
    Page<SocialuniMessageDO> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);

}