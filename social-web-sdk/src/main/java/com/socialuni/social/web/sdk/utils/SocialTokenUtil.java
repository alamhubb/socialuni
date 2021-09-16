package com.socialuni.social.web.sdk.utils;

import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.utils.UUIDUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SocialTokenUtil {
    //key 这样可以保证每次请求生成的token一致，方便测试
    private static String tokenSecretKey;

    @Value("${socialuni.user.token-secret-key:tokenSecretKey}")
    public void setTokenKey(String tokenKey) {
        SocialTokenUtil.tokenSecretKey = tokenKey;
    }

    private static String tokenName;

    @Value("${socialuni.user.token-name:token}")
    public void setTokenName(String tokenName) {
        SocialTokenUtil.tokenName = tokenName;
    }

    public static String getToken() {
        HttpServletRequest request = RequestUtil.getRequest();
        String token = request.getHeader(tokenName);
        if (SocialTokenUtil.isSuccess(token)) {
            return token;
        }
        return SocialTokenUtil.getAttrToken();
    }

    private static String getAttrToken() {
        HttpServletRequest request = RequestUtil.getRequest();
        String token = (String) request.getAttribute(tokenName);
        if (SocialTokenUtil.isSuccess(token)) {
            return token;
        }
        return null;
    }

    public static void setToken(String token) {
        HttpServletRequest request = RequestUtil.getRequest();
        request.setAttribute(tokenName, token);
    }


    public static Boolean hasToken() {
        return SocialTokenUtil.isSuccess(SocialTokenUtil.getToken());
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
            } catch (MalformedJwtException e) {
                throw new SocialSystemException("生成token异常");
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