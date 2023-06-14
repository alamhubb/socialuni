package com.socialuni.social.sdk.dao.repository.community;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkHasUnderageImgAuditDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkAdultImgAuditRepository extends JpaRepository<SocialuniTalkHasUnderageImgAuditDO, Integer> {

    List<SocialuniTalkHasUnderageImgAuditDO> findTop20ByStatusAndDevIdOrderByIdAsc(String status, Integer devId);

    SocialuniTalkHasUnderageImgAuditDO findOneById(Integer id);
}
