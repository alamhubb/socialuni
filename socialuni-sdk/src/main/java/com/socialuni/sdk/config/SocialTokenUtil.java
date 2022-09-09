package com.socialuni.sdk.config;

import com.socialuni.social.web.sdk.config.SocialRequestUserConfig;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import com.socialuni.sdk.utils.UUIDUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SocialTokenUtil {
    //key 这样可以保证每次请求生成的token一致，方便测试
    private static String tokenSecretKey;

    @Value("${socialuni.user.token-secret-key:tokenSecretKey}")
    public void setTokenKey(String tokenKey) {
        SocialTokenUtil.tokenSecretKey = tokenKey;
    }

    public final static String socialuniTokenName = "socialuniToken";

    private static SocialRequestUserConfig socialRequestToken;

    @Resource
    public void setSocialRequestToken(SocialRequestUserConfig socialRequestToken) {
        SocialTokenUtil.socialRequestToken = socialRequestToken;
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NTc3NjRkNmU1ZDE0Yjk4ODMwNDBiZjVjYzY0MjdkMF9lM2E3ZDJlMzFiMDM0M2Q2YjUxMzEwZWZhZmIzNGI5YSJ9.Ccnu2c1NGH2VMFFQcTnCxLNvrJVBljKfd0BDOjQS9J4";
        if (SocialTokenUtil.isSuccess(token)) {
            try {
                String tokenSubject = Jwts.parser().setSigningKey("da838b62ed0e412bb560254ebdc356c1").parseClaimsJws(token).getBody().getSubject();
                System.out.println(tokenSubject.split("_")[0]);
            } catch (Exception e) {
                log.error("生成token异常");
                //必须这么写，不能返回异常，返回异常会记录，会记录用户，会走这里，会循环报错
            }
        }
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
        if (SocialTokenUtil.isSuccess(token)) {
            return token;
        }
        return null;
    }

    public static void setSocialuniToken(String token) {
        HttpServletRequest request = RequestUtil.getRequest();
        request.setAttribute(socialuniTokenName, token);
    }

    public static String generateTokenByUserId(Integer userId) {
        return SocialTokenUtil.generateTokenByUserKey(userId.toString());
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
        if (SocialTokenUtil.isSuccess(token)) {
            try {
                String tokenSubject = Jwts.parser().setSigningKey(SocialTokenUtil.tokenSecretKey).parseClaimsJws(token).getBody().getSubject();
                return tokenSubject.split("_")[0];
            } catch (Exception e) {
                log.error("生成token异常");
                //必须这么写，不能返回异常，返回异常会记录，会记录用户，会走这里，会循环报错
                return null;
            }
        }
//        throw new SocialException("不存在的用户");
        return null;
    }

    public static String getUserKeyByToken() {
        String token = SocialTokenUtil.getToken();
        return SocialTokenUtil.getUserKeyByToken(token);
    }

    //判断是否有效token
    public static boolean isSuccess(String token) {
        return !SocialTokenUtil.isError(token);
    }

    public static Boolean isError(String token) {
        return StringUtils.isEmpty(token)
                || RequestUtil.headerIsEmpty(token)
                || token.length() == 32;
    }
}