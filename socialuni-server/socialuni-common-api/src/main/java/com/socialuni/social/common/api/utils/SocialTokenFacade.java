package com.socialuni.social.common.api.utils;

import com.socialuni.social.common.api.config.SocialRequestUserConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SocialTokenFacade {
    //key 这样可以保证每次请求生成的token一致，方便测试
    private static String tokenSecretKey;

    @Getter
    private static String passwordPublicKey;

    @Getter
    private static String passwordPrivateKey;

    @Value("${socialuni.user.token-secret-key:tokenSecretKey}")
    public void setTokenKey(String tokenKey) {
        SocialTokenFacade.tokenSecretKey = tokenKey;
    }

    @Value("${socialuni.user.password-public-key:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC31h8YE34Wj0VM03Qj2zDryLg3QCJyeWCKLJWd5dUcvgyetnsjDqIH44rgULD6yOeX5T/M337sv3XI7QScXmUn/mVbiAWTKufWmv6hNPQJFoclKmi0B0cHRu0GvrlkB6x2UFMO/Z+2+3UT2kYI/M3EqNUvPDSpNMHIK1/jCB0vawIDAQAB}")
    public void setPasswordPublicKey(String passwordSecretKey) {
        SocialTokenFacade.passwordPublicKey = passwordSecretKey;
    }

    @Value("${socialuni.user.password-private-key:MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfWHxgTfhaPRUzTdCPbMOvIuDdAInJ5YIoslZ3l1Ry+DJ62eyMOogfjiuBQsPrI55flP8zffuy/dcjtBJxeZSf+ZVuIBZMq59aa/qE09AkWhyUqaLQHRwdG7Qa+uWQHrHZQUw79n7b7dRPaRgj8zcSo1S88NKk0wcgrX+MIHS9rAgMBAAECgYAENMp+VwfWBU4tHVN1dJDwfCW24elH3D4Yuz9rHvSa1UBdq0gy+6U6b/rf+giqnEksQQsOKXjyesj7oWyvFTvUPf9opNcbOoGH36JaBbS4DQ1MODM7cyBVtYMrea1Y1Ddvlzz1HxnUEseWAzh/Kxu3Lls768GtH+s9i19UnYup0QJBAOCrCSRLtNNevoA7AdVt09JbNvKClcvcY2FtoVFH0KUCow9WbK0J7Rv8iAkRMTVjzgHC4moaJYv0qrECMqTyn3ECQQDReVZb6lefMfXYp9pvBNdSc+32b0TW+4b8I6Jm5rDo5GqSEoK4kHdlHsfx5fzO3DapZAeCMDdqQQrvaxfIGgabAkBFA8VXJPpzQ9N5SKcTjR1YrTR/4qVpblYJA8heU5VAW0MD3ySbM0VnJ3gScPmQ0Qvjdp5ewZFvIMWegnJUQUPBAkBS0Y1SQ+GmwEL6CMs0744OPGWN9EocUU/6KkCQPI8fVQfJfuYOAJgj87qLqil8w9YcvL63O0b9jH+9TvMVGy0XAkEAvpC84znA6HeuF8R7EFUoGoK4SQgVL0wvd1ut4XaxOzNWVE8Bs1T70kg7APAevN7JNtSjuwMz27dCVXmRbXmNfA==}")
    public void setPasswordPrivateKey(String passwordVector) {
        SocialTokenFacade.passwordPrivateKey = passwordVector;
    }

    public final static String socialuniTokenName = "socialuniToken";

    private static SocialRequestUserConfig socialRequestToken;

    @Resource
    public void setSocialRequestToken(SocialRequestUserConfig socialRequestToken) {
        SocialTokenFacade.socialRequestToken = socialRequestToken;
    }

    public static String getToken() {
        String token = socialRequestToken.getToken();
        /*if (SocialTokenUtil.isSuccess(token)) {
            return token;
        }*/
//        return SocialTokenUtil.getSocialuniToken();
        return token;
    }

    public static String getSocialuniToken() {
        HttpServletRequest request = RequestUtil.getRequest();
        String token = (String) request.getAttribute(socialuniTokenName);
        if (SocialTokenFacade.isSuccess(token)) {
            return token;
        }
        return null;
    }

    public static void setSocialuniToken(String token) {
        HttpServletRequest request = RequestUtil.getRequest();
        request.setAttribute(socialuniTokenName, token);
    }

    public static String generateTokenByUserId(Long userId) {
        return SocialTokenFacade.createTokenByUserKey(userId.toString());
    }

    public static String createTokenByUserKey(String userKey) {
        //生成的时候使用商户号，因为token中不存储敏感信息
        return Jwts.builder()//返回的字符串便是我们的jwt串了
                //先用userid，以后可以改为用uid
                .setSubject(userKey + "_" + UUIDUtil.getUUID())//设置主题
                .signWith(SignatureAlgorithm.HS256, tokenSecretKey)//设置算法（必须）
                .compact();//这个是全部设置完成后拼成jwt串的方法
    }

    //使用websocket时无法获取request必须传入token
    public static String getUserKeyByToken(String token) {
        if (SocialTokenFacade.isSuccess(token)) {
            try {
                String tokenSubject = Jwts.parser().setSigningKey(SocialTokenFacade.tokenSecretKey).parseClaimsJws(token).getBody().getSubject();
                return tokenSubject.split("_")[0];
            } catch (Exception e) {
                log.error("解析token异常");
                //必须这么写，不能返回异常，返回异常会记录，会记录用户，会走这里，会循环报错
                return null;
            }
        }
//        throw new SocialException("不存在的用户");
        return null;
    }

/*    public static String getUserKeyByToken() {
        String token = SocialTokenFacade.getToken();
        return SocialTokenFacade.getUserKeyByToken(token);
    }*/

    //判断是否有效token
    public static boolean isSuccess(String token) {
        return !SocialTokenFacade.isError(token);
    }

    public static Boolean isError(String token) {
        return StringUtils.isEmpty(token)
                || RequestUtil.headerIsEmpty(token);
    }
}