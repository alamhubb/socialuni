package com.socialuni.social.sdk.utils;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.sdk.constant.SocialuniCommonQueryKey;
import com.socialuni.social.sdk.model.QO.SocialuniKeyQueryQO;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialuniCommonQueryUtil {

    public static Map<String, Object> query(SocialuniKeyQueryQO queryData, Object jsonKey) {
        List<String> keys = new ArrayList<>();

        Map<String, Object> keyObject = BeanUtil.beanToMap(jsonKey);

        SocialuniCommonQueryUtil.getFlatKeysByJsonKey(keys, keyObject);

        SocialuniCommonQueryUserRegistry socialuniCommonQueryUserRegistry = new SocialuniCommonQueryUserRegistry();

        List<String> userKeys = socialuniCommonQueryUserRegistry.registryKeys();

        //扁平化map
        HashMap<String, Object> resultMap = new HashMap<>();

        for (String s : keys) {
            //如果包含userKey
            if (userKeys.contains(s)) {
                socialuniCommonQueryUserRegistry.checked(queryData);
                HashMap<String, Object> curMap = socialuniCommonQueryUserRegistry.registryKeyResults(queryData);
                for (String s1 : curMap.keySet()) {
                    resultMap.put(s1, curMap.get(s1));
                }
            }
        }
        //然后将结果按照keys的形式组装map

        SocialuniCommonQueryUtil.getResultByJsonKey(keyObject, resultMap);
        return keyObject;
    }

    public static void getFlatKeysByJsonKey(List<String> keys, Map<String, Object> keyObject) {
        for (String s : keyObject.keySet()) {
            Object value = keyObject.get(s);
            if (value instanceof String) {
                keys.add((String) value);
            } else {
                SocialuniCommonQueryUtil.getFlatKeysByJsonKey(keys, BeanUtil.beanToMap(value));
            }
        }
    }

    public static void getResultByJsonKey(Map<String, Object> keyObject, Map<String, Object> resultObj) {
        for (String s : keyObject.keySet()) {
            Object value = keyObject.get(s);
            if (value instanceof String) {
                keyObject.put(s, resultObj.get((String) value));
            } else {
                SocialuniCommonQueryUtil.getResultByJsonKey(BeanUtil.beanToMap(value), resultObj);
            }
        }
    }

    public static void fasdf() {
        SocialuniKeyQueryQO queryQO = new SocialuniKeyQueryQO();
        Map<String, String> keys = new HashMap<>();
        String key = "asdf";

        //获取包含哪些key

        Map<String, Object> keyMap = new HashMap<>();

        SocialuniUserDo user = null;

        //如果key中包含需要user的则给user赋值

        List<String> hasUserKey = new ArrayList<>(keys.keySet());

        List<String> systemRegistryKeys = ListConvertUtil.objectListToStringList(JavaReflectUtil.getClassStaticFinalFieldValues(SocialuniCommonQueryKey.class));
        //查询参数

        for (String s : systemRegistryKeys) {

            //得到key
            System.out.println(s);
        }


        if (keys.size() > 0) {
            String userId = queryQO.getUserId();
            if (StringUtils.isEmpty(userId)) {
                throw new SocialBusinessException(MessageFormat.format("查询结果：{}，需要查询参数：{}", keys.toString(), "userId"));
            }
            user = SocialuniUserUtil.getUserByUuid(userId);
        }


        if (key.equals("userId")) {
            SocialuniUserUtil.getMineUserUuidIdNotNull();

        }
    }

}
