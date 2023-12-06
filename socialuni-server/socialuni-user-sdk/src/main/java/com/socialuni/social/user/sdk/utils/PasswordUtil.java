package com.socialuni.social.user.sdk.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.utils.NumberUtils;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import org.apache.commons.lang3.StringUtils;

public class PasswordUtil {
//    static AES aes = SecureUtil.aes(SocialTokenFacade.getPasswordPrivateKey().getBytes());
//    static RSA rsa = new RSA(SocialTokenFacade.getPasswordPrivateKey(), SocialTokenFacade.getPasswordPublicKey());

//    static {
//        aes = SecureUtil.aes(SocialTokenFacade.getPasswordPrivateKey().getBytes());
//        rsa = new RSA(SocialTokenFacade.getPasswordPrivateKey(), SocialTokenFacade.getPasswordPublicKey());
//    }

    //解密
    public static String rsaDecode(String password) {
        RSA rsa = new RSA(SocialTokenFacade.getPasswordPrivateKey(), SocialTokenFacade.getPasswordPublicKey());
        Console.log(1111);
        Console.log(rsa);
        String decryptedText = rsa.decryptStr(password, KeyType.PrivateKey);
        return decryptedText;
    }

    //然后将密码存到数据库
    //sha512加密
    public static String sha512Encode(String password) {
        String hashed = DigestUtil.sha512Hex(password);
        return hashed;
    }


    //解密
    /*public static String decode(String password) {
        String content = aes.decryptStr(password);
        return content;
    }


    //加密
    public static String encode(String password) {
        String content = aes.encryptHex(password);
        return content;
    }

    public static String encode(String password, String key) {
        AES aes = SecureUtil.aes(key.getBytes());
        String content = aes.encryptHex(password);
        return content;
    }*/

    public static String check(String cryptoPassword) {
        String password = PasswordUtil.rsaDecode(cryptoPassword);
        if (StringUtils.isEmpty(password)) {
            throw new SocialBusinessException("密码不能为空");
        } else if (password.length() < 8) {
            throw new SocialBusinessException("密码长度不能小于8位");
        } else if (password.length() > 16) {
            throw new SocialBusinessException("密码长度不能大于16位");
        } else if (NumberUtils.strIsAllNumber(password)) {
            throw new SocialBusinessException("密码必须包含字母");
        } else if (NumberUtils.strAllNotNumber(password)) {
            throw new SocialBusinessException("密码必须包含数字");
        }
        return password;
    }


    public static void main(String[] args) {
        RSA rsa = new RSA();
        // 获取私钥和公钥
//        String publicKeyBase64 = rsa.getPublicKeyBase64();
//        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        String publicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCn0fm6oDNoiDGeXm0J5/VFBv+NqD1eaV2fLNCN6FCOHrsLo9DXK2p1A2C1kvFVkebknP6Ss/1J3ptJr7By6Lp0NAdP1UsvHEShjKtgbsKpkkCCY8sqEiJFKUtTmvoinWgH20IwEg+pss7vpqaluWLM3kxVfHqR3RnPszjplNkSMwIDAQAB";
        String privateKeyBase64 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKfR+bqgM2iIMZ5ebQnn9UUG/42oPV5pXZ8s0I3oUI4euwuj0NcranUDYLWS8VWR5uSc/pKz/Unem0mvsHLounQ0B0/VSy8cRKGMq2BuwqmSQIJjyyoSIkUpS1Oa+iKdaAfbQjASD6myzu+mpqW5YszeTFV8epHdGc+zOOmU2RIzAgMBAAECgYBH53/IprtgHnqkEHFHvUspuEXmvB8PRLYIbXsuUkuxmRkrc5UXnFKQsFHPepZMw6babSnmUZhE/kwq1kwT8ANy9A7OVxsvaMhWgj4Sjn+wHb6RGnpKcN5PQcluXIOzgQdlj6XIJXYOMsuwk8XDv7uA20lPrL6Rsdg+FTWpoFWHAQJBAOVtjYqmrQQgkjPLfp1VD5/+071kWALUFW5KgrzEgHlfkJtJRLChDTxRxpNIi8jxG4MO/p6VXMDhK9b02QXkSMkCQQC7QccyfMW9xwCEl+zKHT19IsS9WFWtflJyR/+jQng7tdgK6yMAAg0ji5IbL8rUAaj635dud6ySuNwLDj/pQb0bAkEApneW+8N0RkpbyrkcZYwSikSb3g+wzFyWMDBgLBdoIKj2g7Xl4UL6F4o+Sx+YTXSkQbPfi7FUW58F8DI4Rc4eAQJBAJruwbG0KqfqjzMlwHU53DY9HirLe5ggfHaxu4GI7ZH50ta11i+bguN2B10XHeprtTRc6x4rRk3fRbCgCaElEyECQFjlgKThi9sPFSRm2n8W7LNHt9ab7/gLCx+8mLp/RJWqQ1uae/Z2KKpIauBwmsvNuMFTqYSeZkwHnYEusY7tU4g=";

        // 创建RSA对象，并生成随机的私钥和公钥
        rsa = new RSA(privateKeyBase64, publicKeyBase64);


        // 打印私钥和公钥
        System.out.println("Public Key: " + publicKeyBase64);
        System.out.println("Private Key: " + privateKeyBase64);

        // 要加密的文本
        String text = "Hello, Hutool RSA!";

        // 使用公钥加密
        String encryptedText = rsa.encryptBase64(text, KeyType.PublicKey);
        System.out.println("Encrypted: " + encryptedText);

        // 使用私钥解密
        String decryptedText = rsa.decryptStr(encryptedText, KeyType.PrivateKey);
        System.out.println("Decrypted: " + decryptedText);
    }

}
