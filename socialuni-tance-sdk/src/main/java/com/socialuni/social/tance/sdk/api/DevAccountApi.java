package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountApi {
//    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountModel findOneById(Integer id);

    //直接携带秘钥访问
//    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountModel findOneBySecretKey(String secretKey);

//    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountModel findOneBySocialuniId(String socialuniId);

    DevAccountModel findOneByAppName(String appName);

    DevAccountModel findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<?  extends DevAccountModel> findFirstByOrderByIdDesc();

    //不需要缓存，低频, admin登录使用
    DevAccountModel findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<?  extends DevAccountModel  > findAll();

    DevAccountModel savePut(DevAccountModel devAccountModel);

    //同步创建生产环境的开发者账号
    DevAccountModel queryDevAccount(DevAccountQueryQO devAccountQueryQO);

    DevAccountModel queryDevAccountProvider( DevAccountQueryQO devAccountQueryQO);

    @Data
    @NoArgsConstructor
    public class DevAccountQueryQO {
        @NotBlank
        private String secretKey;

        public DevAccountQueryQO(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}