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

    @Value("${socialuni.user.password-public-key:MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKVzx4CQiv8DXrs/anJXTjtJo5zrZnhClU4xo5TGU00M804rPRiwmArFrljzY5f4VlhJKjMqR9BbYlkqgw4X9p/tMifZ2ONJVMVMXAk3B91pxYRLY6S1p61010Q1gVs/1WtTdQxKsZkBNxYsd6VSZGMAoke1d3J4VYusEQDje8G/AgMBAAECgYA258uibhU6WG90+zADftaO1TqvBe1s5keu+ckbw478SFprq+ysB1qFZKGdkjOQSJC63pja/uJzEgh6p6FzKV+lnFpemsx6uIFe2Uets8uIFe7T2cTRKxstKglnOk+xJ8In7IdoYK6qrBRoYORTxO2BJGExlc7xWRC2lBuk5mMnuQJBAOfv2xdIR4iBqejKcDhhfEOgd8TZN8r9qE8/ycczTVio99ro5vYJ8ZpzoxMVVffrW7z+x4G7z2P+WbArfd+PaikCQQC2nh6UqEMJMnWHZ657nPtXuZI2dItGWAVJHBScHkhLFOOv1OKnOLbyqndf1iPPS8Yzp9tAC4ZDnikU1KNYA5mnAkB9dybRhWwMz/eV0ky6cyHBg4DGn6qqyGTP2fXRKFNi7qSFaw/lcKOdpYhFvnju0AakSj6qU2pw8RqbTiudT/thAkEAncpEHCjDO4go+yWAIvkCR8tlZb4PtWlKr+AegsK7rnxPO9eTO/AKS+U1Hk7WREBx8qJAFLegfH6FeqU8ukjqEwJBAJHJFIJipcciouh0mh/h2zvzozeFljGAWDsMB0Ij2VJDq2MPhxRQgRUsDn2KZbm4pIwCK6xJiAWZC/qbfFzoK34=}")
    public void setPasswordPublicKey(String passwordSecretKey) {
        SocialTokenFacade.passwordPublicKey = passwordSecretKey;
    }

    @Value("${socialuni.user.password-private-key:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClc8eAkIr/A167P2pyV047SaOc62Z4QpVOMaOUxlNNDPNOKz0YsJgKxa5Y82OX+FZYSSozKkfQW2JZKoMOF/af7TIn2djjSVTFTFwJNwfdacWES2OktaetdNdENYFbP9VrU3UMSrGZATcWLHelUmRjAKJHtXdyeFWLrBEA43vBvwIDAQAB}")
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

    public static String generateTokenByUserId(Integer userId) {
        return SocialTokenFacade.generateTokenByUserKey(userId.toString());
    }

    public static String generateTokenByUserKey(String userKey) {
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

    public static String getUserKeyByToken() {
        String token = SocialTokenFacade.getToken();
        return SocialTokenFacade.getUserKeyByToken(token);
    }

    //判断是否有效token
    public static boolean isSuccess(String token) {
        return !SocialTokenFacade.isError(token);
    }

    public static Boolean isError(String token) {
        return StringUtils.isEmpty(token)
                || RequestUtil.headerIsEmpty(token);
    }
}