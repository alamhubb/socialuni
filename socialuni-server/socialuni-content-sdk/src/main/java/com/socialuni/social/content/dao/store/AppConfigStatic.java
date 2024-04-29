package com.socialuni.social.content.dao.store;


import com.socialuni.social.content.dao.DO.IllegalWordDO;
import com.socialuni.social.content.dao.DO.KeywordsDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AppConfigStatic {
    private static Integer[] boysFaceValues = new Integer[0];
    private static Integer[] girlsFaceValues = new Integer[0];


    public static void setBoysFaceValues(Integer[] boysFaceValues) {
        AppConfigStatic.boysFaceValues = boysFaceValues;
    }

    public static void setGirlsFaceValues(Integer[] girlsFaceValues) {
        AppConfigStatic.girlsFaceValues = girlsFaceValues;
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
        AppConfigStatic.keywordDOs = keywordDOs;
        log.info("更新违禁词，违禁词数量：" + AppConfigStatic.keywordDOs.size());
    }

    public static List<KeywordsDO> getKeywordDOs() {
        return AppConfigStatic.keywordDOs;
    }

    public static void setIllegals(List<IllegalWordDO> illegals) {
        AppConfigStatic.illegals = illegals;
        log.info("更新违禁词，违禁词数量：" + AppConfigStatic.illegals.size() + "，违禁词列表：" + AppConfigStatic.illegals);
    }

    public static List<IllegalWordDO> getIllegals() {
        return AppConfigStatic.illegals;
    }
}
