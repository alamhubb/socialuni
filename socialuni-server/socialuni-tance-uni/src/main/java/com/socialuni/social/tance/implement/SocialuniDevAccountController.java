/*
package com.socialuni.social.tance.implement;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tance.model.DO.DevAccountDo;
import com.socialuni.social.tance.repository.DevAccountRepository;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


*/
/**
 * @author qinkaiyuan
 * @date 2021-07-28 11:09
 * 前端初始化内容
 *//*

@RequestMapping("devAccount")
@Component("devAccountApi") //
public class SocialuniDevAccountController  implements DevAccountInterface {
    @Autowired
    private DevAccountRepository devAccountRepository;

    @Override
    public DevAccountModel findOneById(Integer id) {
        return devAccountRepository.findOneById(id);
    }

    @Override
    public DevAccountModel findOneBySecretKey(String secretKey) {
        return devAccountRepository.findOneBySecretKey(secretKey);
    }

    @Override
    public DevAccountModel findOneBySocialuniId(String socialuniId) {
        return devAccountRepository.findOneBySocialuniId(socialuniId);
    }

    @Override
    public DevAccountModel findOneByAppName(String appName) {
        return devAccountRepository.findOneByAppName(appName);
    }

    @Override
    public DevAccountModel findOneByDevNum(Long devNum) {
        return devAccountRepository.findOneByDevNum(devNum);
    }

    @Override
    public Optional<DevAccountDo> findFirstByOrderByIdDesc() {
        return devAccountRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public DevAccountModel findOneByPhoneNumOrderByIdAsc(String phoneNum) {
        return devAccountRepository.findOneByPhoneNumOrderByIdAsc(phoneNum);
    }

    @Override
    public List<DevAccountDo> findAll() {
        return devAccountRepository.findAll();
    }

    @Override
    public DevAccountModel savePut(DevAccountModel devAccountModel) {
        return devAccountRepository.savePut(devAccountModel);
    }

    */
/*@Override
    public DevAccountModel save(DevAccountModel devAccountModel) {
        DevAccountModel save = devAccountRepository.save( toDevAccountDo(devAccountModel) );
        return save;
    }*//*


    @Override
    public DevAccountModel queryDevAccount(DevAccountQueryQO devAccountQueryQO) {
        return null;
    }

    @Override
    public DevAccountModel queryDevAccountProvider(DevAccountQueryQO devAccountQueryQO) {
        return null;
    }

    public DevAccountDo toDevAccountDo(DevAccountModel devAccountModel){
        DevAccountDo devAccountDo = BeanUtil.copyProperties(devAccountModel, DevAccountDo.class);
        return devAccountDo;
    }

}
*/
