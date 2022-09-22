package com.socialuni.sdk.dao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.qcloud.cos.model.ciModel.auditing.UserInfo;
import com.socialuni.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.sdk.model.RO.UserOAuthRO;
import com.socialuni.sdk.model.RO.user.SocialuniContentIdRO;
import com.socialuni.sdk.utils.ObjectUtil;
import com.socialuni.social.web.sdk.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
@Slf4j
public class SocialuniUserInfoDOUtil {
    public static void main(String[] args) throws JsonProcessingException {
        TypeTest<UserOAuthRO> typeTest1 = new TypeTest<UserOAuthRO>() {
        };
        typeTest1.testT();
        // 带花括号是可以拿到的。不带的话拿不到
//        BaseDaoImpl<UserOAuthRO> baseDao = new BaseDaoImpl<>();


        /*UserOAuthRO userOAuthRO = SocialuniUserInfoDOUtil.createObj();

        BaseDao<UserOAuthRO> baseDao = new BaseDao<>();*/

//        System.out.println(baseDao.);
    }

    public static <T> T createObj() throws JsonProcessingException {
//        TypeTest<T> typeTest1=new TypeTest<T>(){};
        // 带花括号是可以拿到的。不带的话拿不到
//        typeTest1.testT();
        /*GenericClass<T> genericsClass = new GenericClass<>();

        System.out.println(genericsClass.getClass());

        ParameterizedType genericType = ((ParameterizedType) genericsClass.getClass().getGenericSuperclass());
        Type[] arguments = genericType.getActualTypeArguments();
        for (Type type : arguments) {
            System.out.println(type);
        }
//        return name;
        Type mySuperClass = genericsClass.getClass().getGenericInterfaces()[0];
        //多加了一层instanceof的判断
        if (mySuperClass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
            while (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getRawType();
            }
            String className = type.toString();
            if (className.startsWith("class ")) {
                className = className.substring(6);
            } else if (className.startsWith("interface ")) {
                className = className.substring(10);
            }
            System.out.println(className);
        }*/

        /*ParameterizedType genericType = ((ParameterizedType) genericsClass.getClass().getGenericSuperclass());
        Type[] arguments = genericType.getActualTypeArguments();
        for (Type type : arguments) {
            System.out.println(type);
        }*/

        return null;
    }
}
