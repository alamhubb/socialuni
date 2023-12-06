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
        String publicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC31h8YE34Wj0VM03Qj2zDryLg3QCJyeWCKLJWd5dUcvgyetnsjDqIH44rgULD6yOeX5T/M337sv3XI7QScXmUn/mVbiAWTKufWmv6hNPQJFoclKmi0B0cHRu0GvrlkB6x2UFMO/Z+2+3UT2kYI/M3EqNUvPDSpNMHIK1/jCB0vawIDAQAB";
        String privateKeyBase64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfWHxgTfhaPRUzTdCPbMOvIuDdAInJ5YIoslZ3l1Ry+DJ62eyMOogfjiuBQsPrI55flP8zffuy/dcjtBJxeZSf+ZVuIBZMq59aa/qE09AkWhyUqaLQHRwdG7Qa+uWQHrHZQUw79n7b7dRPaRgj8zcSo1S88NKk0wcgrX+MIHS9rAgMBAAECgYAENMp+VwfWBU4tHVN1dJDwfCW24elH3D4Yuz9rHvSa1UBdq0gy+6U6b/rf+giqnEksQQsOKXjyesj7oWyvFTvUPf9opNcbOoGH36JaBbS4DQ1MODM7cyBVtYMrea1Y1Ddvlzz1HxnUEseWAzh/Kxu3Lls768GtH+s9i19UnYup0QJBAOCrCSRLtNNevoA7AdVt09JbNvKClcvcY2FtoVFH0KUCow9WbK0J7Rv8iAkRMTVjzgHC4moaJYv0qrECMqTyn3ECQQDReVZb6lefMfXYp9pvBNdSc+32b0TW+4b8I6Jm5rDo5GqSEoK4kHdlHsfx5fzO3DapZAeCMDdqQQrvaxfIGgabAkBFA8VXJPpzQ9N5SKcTjR1YrTR/4qVpblYJA8heU5VAW0MD3ySbM0VnJ3gScPmQ0Qvjdp5ewZFvIMWegnJUQUPBAkBS0Y1SQ+GmwEL6CMs0744OPGWN9EocUU/6KkCQPI8fVQfJfuYOAJgj87qLqil8w9YcvL63O0b9jH+9TvMVGy0XAkEAvpC84znA6HeuF8R7EFUoGoK4SQgVL0wvd1ut4XaxOzNWVE8Bs1T70kg7APAevN7JNtSjuwMz27dCVXmRbXmNfA==";

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
