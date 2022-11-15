package com.socialuni.social.common.sdk.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 利用redis+lua优雅实现每天给用户发短信次数限制
 * https://blog.csdn.net/weixin_38640456/article/details/125768957
 */
public class RedisLimitTools {
    private int limitCount;

    private StringRedisTemplate redisTemplate;

    public void test(String key) {
        //调用lua脚本并执行
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);//返回类型是Long
        redisScript.setScriptText(
                "local sms_send_key = KEYS[1];\n" +
                "local sms_send_limit = ARGV[1];\n" +
                "local expire_time = ARGV[2];\n" +
                "local oldValue = redis.call(\"get\",sms_send_key);\n" +
                "if (oldValue == nil) then\n" +
                "\t--- 之前不存在\n" +
                "\tredis.call(\"incr\",sms_send_key);\n" +
                "\tredis.call(\"expire\",sms_send_key,expire_time);\n" +
                "\treturn true;\n" +
                "elseif  ( tonumber(oldValue) > sms_send_limit )  then\n" +
                "\t--- 自增\n" +
                "\tredis.call(\"incr\",sms_send_key);\n" +
                "\treturn true;\n" +
                "else \n" +
                "\t--- 失败，不操作。\n" +
                "\treturn false;\n" +
                "end");
        LocalDateTime now = LocalDateTime.now();
        int dayOfYear = now.getDayOfYear();
        int minute = now.getMinute();
        int hour = now.getHour();

        String userId = "123";
        //默认value
        String limitValue = "1."+(hour * 60 + minute);
        //当晚23:59:59秒过期
        int expireTime = (24*60 - (hour * 60 + minute))*60;
        Boolean ans = redisTemplate.execute(redisScript, Arrays.asList("sms_limit_key:"+userId+":"+dayOfYear), limitValue,String.valueOf(expireTime));
        if(ans) {
            System.out.println("发短信给用户");
        }
    }

}

