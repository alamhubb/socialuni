package com.socialuni.social.im.dao.repository;

import com.socialuni.social.im.dao.DO.SocialuniChatDO;
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
public interface ChatRepository extends JpaRepository<SocialuniChatDO, Long> {

    //未登录只能查询官方的消息列表
    List<SocialuniChatDO> findByStatusAndTypeInOrderByTopLevelDescUpdateTimeDesc(String status, List<String> types);

    //查询对应的chat,读取时，任何类型的chat都可以改为已读，但是sys类型不操作
    Optional<SocialuniChatDO> findFirstByIdAndStatus(Integer id, String status);

    //开启时，只有私聊的才能开启
    Optional<SocialuniChatDO> findFirstByIdAndTypeAndStatus(Integer id, String type, String status);

    //用户注册的时候查询系统群聊，把用户加入启用的系统群聊
    List<SocialuniChatDO> findByTypeAndStatus(String type, String status);
}