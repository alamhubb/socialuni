package com.socialuni.admin.web.model;

import com.socialuni.social.sdk.dao.DO.keywords.KeywordsDO;
import com.socialuni.social.sdk.dao.DO.keywords.KeywordsTriggerDetailDO;
import lombok.Data;

import java.util.List;

@Data
public class KeywordsDetailVO {
    //db的keywords
    private KeywordsDO tempKeywords;
    //实时计算的keywords
    private KeywordsDO keywords;

    private List<KeywordsTriggerDetailDO> triggerDetails;
    private List<KeywordsTriggerDetailDO> vioTriggerDetails;
}
