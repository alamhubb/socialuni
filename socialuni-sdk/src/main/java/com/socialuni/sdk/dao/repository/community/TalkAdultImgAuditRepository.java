package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkHasUnderageImgAuditDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkAdultImgAuditRepository extends JpaRepository<SocialuniTalkHasUnderageImgAuditDO, Integer> {

    List<SocialuniTalkHasUnderageImgAuditDO> findTop20ByStatusOrderByIdAsc(String status);
    SocialuniTalkHasUnderageImgAuditDO findOneById(Integer id);
}
