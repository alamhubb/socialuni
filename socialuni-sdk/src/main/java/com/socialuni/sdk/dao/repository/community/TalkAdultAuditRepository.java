package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkAdultAuditDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkAdultAuditRepository extends JpaRepository<SocialTalkAdultAuditDO, Integer> {

    List<SocialTalkAdultAuditDO> findTop20ByStatusInOrderByIdAsc(String status);
}
