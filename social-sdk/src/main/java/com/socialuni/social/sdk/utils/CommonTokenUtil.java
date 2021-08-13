package com.socialuni.social.sdk.utils;

import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.entity.model.DO.user.TokenDO;
import com.socialuni.social.sdk.repository.CommonTokenRepository;
import com.socialuni.social.utils.RequestUtil;
import com.socialuni.social.utils.UUIDUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class CommonTokenUtil {
    //key 这样可以保证每次请求生成的token一致，方便测试
    private static String tokenSecretKey;

    private static CommonTokenRepository commonTokenRepository;

    @Resource
    public void setCommonTokenRepository(CommonTokenRepository commonTokenRepository) {
        CommonTokenUtil.commonTokenRepository = commonTokenRepository;
    }

    @Value("${socialuni.user.token-secret-key:tokenSecretKey}")
    public void setTokenKey(String tokenKey) {
        CommonTokenUtil.tokenSecretKey = tokenKey;
    }

    private static String tokenName;

    @Value("${socialuni.user.token-name:token}")
    public void setTokenName(String tokenName) {
        CommonTokenUtil.tokenName = tokenName;
    }

    public static String getToken() {
        HttpServletRequest request = RequestUtil.getRequest();
        String token = request.getHeader(tokenName);
        if (CommonTokenUtil.isSuccess(token)) {
            return token;
        }
        return null;
    }

    public static Boolean hasToken() {
        return CommonTokenUtil.isSuccess(CommonTokenUtil.getToken());
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
        if (CommonTokenUtil.isSuccess(token)) {
            try {
                String tokenSubject = Jwts.parser().setSigningKey(CommonTokenUtil.tokenSecretKey).parseClaimsJws(token).getBody().getSubject();
                return tokenSubject.split("_")[0];
            } catch (MalformedJwtException e) {
                log.info(e.getMessage());
            }
        }
//        throw new SocialException("不存在的用户");
        return null;
    }

    public static String getUserKeyByToken() {
        String token = CommonTokenUtil.getToken();
        return CommonTokenUtil.getUserKeyByToken(token);
    }

    public static TokenDO getCommonTokenDO() {
        String token = CommonTokenUtil.getToken();
        return CommonTokenUtil.getCommonTokenDO(token);
    }

    public static TokenDO getCommonTokenDO(String token) {
        String userKey = CommonTokenUtil.getUserKeyByToken(token);
        if (userKey == null) {
            return null;
        }
        //解析token
        Integer userId = Integer.valueOf(userKey);
        TokenDO tokenDO = commonTokenRepository.findOneByToken(token);
        if (tokenDO == null) {
            throw new SocialParamsException("不存在的用户");
        }
        Date date = new Date();
        //如果当前时间大于时效时间，则时效了
        if (date.getTime() > tokenDO.getExpiredTime().getTime()) {
            throw new SocialBusinessException("用户凭证过期，请重新登录");
        }
        Integer doUserId = tokenDO.getUserId();
        if (!userId.equals(doUserId)) {
            log.error("绕过验证，错误的userId:{},{}", doUserId, userId);
            throw new SocialParamsException("不存在的用户");
        }
        //返回user
        return tokenDO;
    }

    //判断是否有效token
    public static boolean isSuccess(String token) {
        return !CommonTokenUtil.isError(token);
    }

    public static Boolean isError(String token) {
        return StringUtils.isEmpty(token)
                || "undefined".equals(token)
                || "null".equals(token)
                || "[object Null]".equals(token)
                || token.length() == 32;
    }
}