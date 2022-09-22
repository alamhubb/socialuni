package com.socialuni.sdk.dao.utils;

import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;

public class SocialuniUserInfoDOUtil {
    public static <T extends SocialUnionContentBaseDO> T save(T t) throws InstantiationException, IllegalAccessException {
        Class<T> tClass = (Class<T>) t.getClass();

        Table tableAnnotation = tClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new SocialParamsException("实体类缺少@Table注解");
        }
        String tableName = tableAnnotation.name();
        if (StringUtils.isEmpty(tableName)) {
            throw new SocialParamsException("table表名不能为空");
        }

        System.out.println(tableAnnotation.name());
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(tableName);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //遍历这个t，生成sql

//        entityManager.createQuery("insert into (" + t.getContent() + ") tableName");
        return null;
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        SocialuniTalkDO socialuniTalkDO = SocialuniUserInfoDOUtil.save(new SocialuniTalkDO());
    }
}