package com.socialuni.sdk.dao.repository.community;

import com.socialuni.sdk.dao.DO.ReportDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgAdultAuditDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkAdultImgAuditRepository extends JpaRepository<SocialTalkImgAdultAuditDO, Integer> {

    List<SocialTalkImgAdultAuditDO> findTop20ByStatusOrderByIdAsc(String status);
}
