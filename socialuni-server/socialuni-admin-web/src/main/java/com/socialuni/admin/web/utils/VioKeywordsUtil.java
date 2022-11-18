package com.socialuni.admin.web.utils;


import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;

import java.util.*;

public class VioKeywordsUtil {

    public static void gatherKeywords(List<?  extends SocialuniTalkDO>  talkDOS) {
        Map<String, Integer> keyMap = new HashMap<>();
        for (SocialuniTalkDO talkDO : talkDOS) {
            String text = talkDO.getContent();
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                int keyMaxCount = 9;
                for (int j = 0; j < keyMaxCount; j++) {
                    int endIndex = i + 2 + j;
                    if (endIndex > textLength) {
                        break;
                    }
                    String key = text.substring(i, endIndex);
                    keyMap.merge(key, 1, Integer::sum);
                }
            }
        }

        Map<String, Integer> sortMap = sortMapByValue(keyMap);
    }

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(
                oriMap.entrySet());
        entryList.sort(new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }
    }
}
