package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.event.ddd.EventPublisherFacade;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPasswordDO;
import com.socialuni.social.user.sdk.dao.DO.SocialUserPhoneDo;
import com.socialuni.social.user.sdk.logic.entity.SocialPhoneLoginEntity;
import com.socialuni.social.user.sdk.logic.entity.SocialProviderLoginEntity;
import com.socialuni.social.user.sdk.logic.manage.SocialUserPhoneManage;
import com.socialuni.social.user.sdk.logic.manage.SocialuniTokenManage;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneAuthCodePasswordQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumPasswordQO;
import com.socialuni.social.user.sdk.model.QO.SocialPhoneNumAuthCodeQO;
import com.socialuni.social.user.sdk.model.QO.SocialProviderLoginQO;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.model.factory.SocialuniMineUserROFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class SocialuniLoginDomain {
    @Resource
    SocialuniTokenManage tokenManage;
    @Resource
    SocialPhoneLoginEntity socialPhoneLoginEntity;

    @Resource
    SocialProviderLoginEntity socialProviderLoginEntity;


    @Transactional
    public SocialLoginRO<SocialuniUserRO> providerLogin(SocialProviderLoginQO loginQO) {
        //创建或返回
        SocialuniUserDo mineUser = socialProviderLoginEntity.providerLogin(loginQO);

        return getSocialLoginROByMineUser(mineUser);
    }

    @Transactional
    public SocialLoginRO<SocialuniUserRO> phoneLogin(SocialPhoneNumAuthCodeQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        return getSocialLoginROByMineUser(mineUser);
    }

    @Resource
    SocialUserPhoneManage socialUserPhoneManage;

    @Transactional
    public SocialLoginRO<SocialuniUserRO> passwordLogin(SocialPhoneNumPasswordQO socialPhoneNumQO) {
        String phoneNum = socialPhoneNumQO.getPhoneNum();

        SocialUserPhoneDo socialUserPhoneDo = socialUserPhoneManage.checkLoginPhoneNumAndGetUser(phoneNum);

        if (socialUserPhoneDo == null) {
            throw new SocialSystemException("手机号未注册，却执行了密码登录");
        }

        Integer userId = socialUserPhoneDo.getUserId();

        SocialUserPasswordDO socialUserPasswordDO = SocialuniUserRepositoryFacede.findByUserId(userId, SocialUserPasswordDO.class);

        if (socialUserPasswordDO == null) {
            throw new SocialSystemException("未设置密码，却使用了密码登录");
        }

        String password = socialUserPasswordDO.getPassword();
        //aes解密 密码
        String realPassword = null;


//        SocialTokenFacade.getPasswordSecretKey();


        return null;
//        return getSocialLoginROByMineUser(mineUser);
    }

    public static String decrypt(String encryptedData, String secretKey, String initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        System.out.println(decrypt("你的加密文本", key, initVector));
    }

    public static String toSHA512(String input) {
        try {
            // 创建一个 MessageDigest 实例，初始化为 SHA-512 算法
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // 对输入字符串进行加密
            byte[] hashedBytes = md.digest(input.getBytes());

            // 将字节数组转换为十六进制的字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public SocialLoginRO<SocialuniUserRO> passwordPhoneLogin(SocialPhoneAuthCodePasswordQO socialPhoneNumQO) {
        SocialuniUserDo mineUser = socialPhoneLoginEntity.phoneLogin(socialPhoneNumQO);

        SocialUserPasswordDO socialUserPasswordDO = SocialuniUserRepositoryFacede.findByUserId(mineUser.getUserId(), SocialUserPasswordDO.class);
        if (socialUserPasswordDO != null) {

        } else {

        }


        return getSocialLoginROByMineUser(mineUser);
    }


    public SocialLoginRO<SocialuniUserRO> getSocialLoginROByMineUser(SocialuniUserDo mineUser) {
        SocialuniUserRO userDetailRO = SocialuniMineUserROFactory.getMineUser(mineUser);

        SocialuniTokenDO socialUserTokenDO = tokenManage.create(mineUser.getUnionId());


        EventPublisherFacade.publishEvent("userLogin", mineUser);

        return new SocialLoginRO<>(socialUserTokenDO.getToken(), userDetailRO);
    }
}
