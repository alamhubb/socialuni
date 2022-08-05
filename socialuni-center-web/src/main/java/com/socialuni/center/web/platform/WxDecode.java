package com.socialuni.center.web.platform;

import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.center.web.utils.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;

/**
 * @author qinkaiyuan
 * @date 2020-03-26 13:30
 */
public class WxDecode {
    public static String decrypt(String key, String data, String iv) {
        if (StringUtils.isEmpty(key)) {
            throw new SocialParamsException("三方平台授权问题，返回了空的sessionKey");
        }
        if (StringUtil.hasEmpty(data, iv)) {
            throw new SocialParamsException("解密数据包含空参数");
        }
        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
