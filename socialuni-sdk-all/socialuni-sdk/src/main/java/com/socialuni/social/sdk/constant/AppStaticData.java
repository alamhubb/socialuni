package com.socialuni.social.sdk.constant;


import com.socialuni.social.user.sdk.model.DO.IllegalWordDO;
import com.socialuni.social.user.sdk.model.DO.keywords.KeywordsDO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AppStaticData {
    private static Integer[] boysFaceValues = new Integer[0];
    private static Integer[] girlsFaceValues = new Integer[0];

    public static void setBoysFaceValues(Integer[] boysFaceValues) {
        AppStaticData.boysFaceValues = boysFaceValues;
    }

    public static void setGirlsFaceValues(Integer[] girlsFaceValues) {
        AppStaticData.girlsFaceValues = girlsFaceValues;
    }

    public static Integer[] getBoysFaceValueList() {
        return boysFaceValues;
    }

    public static Integer[] getGirlsFaceValueList() {
        return girlsFaceValues;
    }

    //1171970219 用户要求此qq加入违禁词
    private static List<IllegalWordDO> illegals = new ArrayList<>();

    private static List<KeywordsDO> keywordDOs = new ArrayList<>();

    public static void setKeywordDOs(List<KeywordsDO> keywordDOs) {
        AppStaticData.keywordDOs = keywordDOs;
        log.info("更新违禁词，违禁词数量：" + AppStaticData.keywordDOs.size());
    }

    public static List<KeywordsDO> getKeywordDOs() {
        return AppStaticData.keywordDOs;
    }

    public static void setIllegals(List<IllegalWordDO> illegals) {
        AppStaticData.illegals = illegals;
        log.info("更新违禁词，违禁词数量：" + AppStaticData.illegals.size() + "，违禁词列表：" + AppStaticData.illegals);
    }

    public static List<IllegalWordDO> getIllegals() {
        return AppStaticData.illegals;
    }
}
