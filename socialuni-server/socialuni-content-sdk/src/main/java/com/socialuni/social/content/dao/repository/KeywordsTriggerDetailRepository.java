package com.socialuni.social.content.dao.repository;


import com.socialuni.social.content.dao.DO.KeywordsTriggerDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-27 23:21
 */
public interface KeywordsTriggerDetailRepository extends JpaRepository<KeywordsTriggerDetailDO, Integer> {

    List<KeywordsTriggerDetailDO> findAllByKeywordsId(Integer keywordsId);
    List<KeywordsTriggerDetailDO> findAllByReportId(Integer reportId);
    List<KeywordsTriggerDetailDO> findTop100ByKeywordsId(Integer keywordsId);
}