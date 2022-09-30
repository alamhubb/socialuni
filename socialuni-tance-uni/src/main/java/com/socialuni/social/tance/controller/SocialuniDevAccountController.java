package com.socialuni.social.tance.controller;

import com.socialuni.social.tance.sdk.api.DevAccountApi;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 */
@RequestMapping("devAccount")
@RestController("devAccountApi") //
public class SocialuniDevAccountController  implements DevAccountApi {

    @Override
    public DevAccountModel findOneById(Integer id) {
        return null;
    }

    @Override
    public DevAccountModel findOneBySecretKey(String secretKey) {
        return null;
    }

    @Override
    public DevAccountModel findOneBySocialuniId(String socialuniId) {
        return null;
    }

    @Override
    public DevAccountModel findOneByAppName(String appName) {
        return null;
    }

    @Override
    public DevAccountModel findOneByDevNum(Long devNum) {
        return null;
    }

    @Override
    public Optional<DevAccountModel> findFirstByOrderByIdDesc() {
        return Optional.empty();
    }

    @Override
    public DevAccountModel findOneByPhoneNumOrderByIdAsc(String phoneNum) {
        return null;
    }

    @Override
    public List<DevAccountModel> findAll() {
        return null;
    }

    @Override
    public DevAccountModel save(DevAccountModel devAccountModel) {
        return null;
    }

    @Override
    public DevAccountModel queryDevAccount(DevAccountQueryQO devAccountQueryQO) {
        return null;
    }

    @Override
    public DevAccountModel queryDevAccountProvider(DevAccountQueryQO devAccountQueryQO) {
        return null;
    }
}
