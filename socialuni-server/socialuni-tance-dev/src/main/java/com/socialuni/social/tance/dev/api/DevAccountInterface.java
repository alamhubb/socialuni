package com.socialuni.social.tance.dev.api;

import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountInterface {
//    DevAccountModel savePut(DevAccountModel devAccountModel);
    DevAccountDo save(DevAccountDo devAccountDo);
    //    @Cacheable(cacheNames = "getDevAccountById", key = "#id")
    DevAccountDo findFirstById(Integer id);
    DevAccountDo findFirstByUserId(Long id);

    //直接携带秘钥访问
//    @Cacheable(cacheNames = "getDevAccountBySecretKey", key = "#secretKey")
    DevAccountDo findOneBySecretKey(String secretKey);

    //    @Cacheable(cacheNames = "getDevAccountBySocialuniId", key = "#socialuniId")
    DevAccountDo findOneBySocialuniId(String socialuniId);

    DevAccountDo findOneByAppName(String appName);

    DevAccountDo findOneByDevNum(Long devNum);

    //获取最新的开发者账户，用来id相加，不缓存，低频，创建时才是用
    Optional<? extends DevAccountDo> findFirstByOrderByIdDesc();

    //不需要缓存，低频, admin登录使用
    DevAccountDo findOneByPhoneNumOrderByIdAsc(String phoneNum);

    List<? extends DevAccountDo> findAll();


    //同步创建生产环境的开发者账号
//    DevAccountModel queryDevAccount(DevAccountQueryQO devAccountQueryQO);

//    DevAccountModel queryDevAccountProvider( DevAccountQueryQO devAccountQueryQO);

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