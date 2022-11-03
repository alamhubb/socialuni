package com.socialuni.social.user.sdk.facade;

import com.socialuni.social.common.sdk.utils.RequestUtil;
import com.socialuni.social.common.sdk.utils.UUIDUtil;
import com.socialuni.social.user.sdk.api.SocialRequestUserConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SocialTokenFacade {
    //key 这样可以保证每次请求生成的token一致，方便测试
    private static String tokenSecretKey;

    @Value("${socialuni.user.token-secret-key:tokenSecretKey}")
    public void setTokenKey(String tokenKey) {
        SocialTokenFacade.tokenSecretKey = tokenKey;
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
                || RequestUtil.headerIsEmpty(token)
                || token.length() == 32;
    }
}