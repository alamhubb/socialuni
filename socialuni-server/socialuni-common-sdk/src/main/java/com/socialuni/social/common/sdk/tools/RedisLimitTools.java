package com.socialuni.social.common.sdk.tools;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 利用redis+lua优雅实现每天给用户发短信次数限制
 * https://blog.csdn.net/weixin_38640456/article/details/125768957
 */
public class RedisLimitTools {
    private final int limitCount;
    private final String prefix;

    /**
     *
     * @param limitCount 限制数量
     * @param prefix 标识的前缀。
     */
    public RedisLimitTools(int limitCount, String prefix) {
        this.limitCount = limitCount;
        this.prefix = prefix + ":";
    }

    private StringRedisTemplate redisTemplate;

    /**
     * 执行限制的方法。
     * @param key 标识
     * @param biConsumer 业务方法函数。
     */
    public void execLimit(String key, BiConsumer<String,Boolean> biConsumer) {
        // 单例模式。
        if(redisTemplate == null){
            redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        }
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
        //默认value
        //当晚23:59:59秒过期
        DateTime dateTime = DateUtil.endOfDay(new Date());
        Boolean ans = redisTemplate.execute(redisScript, Arrays.asList(prefix+key), limitCount, dateTime);
        // 执行远程的函数。
        biConsumer.accept(key,ans);
    }

}

