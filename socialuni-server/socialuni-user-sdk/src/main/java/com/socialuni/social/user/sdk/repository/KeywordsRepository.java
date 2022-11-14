package com.socialuni.social.user.sdk.repository;

import com.socialuni.social.user.sdk.model.DO.keywords.KeywordsDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface KeywordsRepository extends JpaRepository<KeywordsDO, Integer> {

    Optional<KeywordsDO> findTopOneByText(String word);

    List<KeywordsDO> findAllByStatusIsNull();
    List<KeywordsDO> findAllByStatus(String status);

    //状态启用的，且文本违规率大于的，且开启了文本的
    List<KeywordsDO> findAllByStatusIsNullAndTextViolateRatioGreaterThanAndOpenTextTrue(Double violateRatio);

    List<KeywordsDO> findAllByStatusIsNullAndTotalNumGreaterThanOrderByTextViolateRatioDesc(Integer num);

    //后台管理，查询关键词列表，按文本违规排序
    List<KeywordsDO> findAllByStatusOrderByTextViolateRatioDesc(String status);

    //后台管理，查询关键词列表，按文本违规排序,开启，打开拼音，且拼音不违规大于19，且违规率小于40
    List<KeywordsDO> findAllByStatusAndOpenPinyinIsTrueAndPinyinNormalNumGreaterThanAndPinyinViolateRatioLessThan(String status,Integer normalCount,Double violateRation);

//    List<KeywordsDO> findTop100ByStatusIsNullOrderByViolateNumDesc();

    List<KeywordsDO> findTop100ByStatusIsNullOrderByTotalNumDesc();

    List<KeywordsDO> findTop100ByStatusIsNullOrderByViolateRatioDesc();

    List<KeywordsDO> findTop100ByStatusIsNullAndTotalNumGreaterThanOrderByViolateRatioDesc(Integer num);

}


