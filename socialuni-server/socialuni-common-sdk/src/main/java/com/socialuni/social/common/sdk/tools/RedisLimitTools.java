package com.socialuni.social.common.sdk.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

    public void test() {

        //调用lua脚本并执行
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);//返回类型是Long
        redisScript.setScriptText("local sms_send_key = KEYS[1];\n" +
                "local sms_send_value = ARGV[1];\n" +
                "local expire_time = ARGV[2];\n" +
                "--解析string字符串 分解出.前缀和.后缀(str = '1.234'  prefix = 1 suffix = 234)\n" +
                "local function parse(str)\n" +
                "    local start_index ,end_index = string.find(str,\".\",1)\n" +
                "    local prefix = string.sub(str,1,start_index)\n" +
                "    local suffix = string.sub(str,start_index+2,-1)\n" +
                "    return prefix,suffix\n" +
                "end\n" +
                " \n" +
                "local value  = redis.call(\"setnx\",sms_send_key,sms_send_value)\n" +
                "--设置成功 说明之前不存在\n" +
                "if(value == 1)\n" +
                "      then   redis.call(\"expire\",sms_send_key,expire_time)\n" +
                "      return true;\n" +
                "else\n" +
                "      local oldValue = tostring(redis.call(\"get\",sms_send_key))\n" +
                "      local oldPre,oldSuf = parse(oldValue)\n" +
                "      local newPre,newSuf = parse(sms_send_value)\n" +
                "--超过60分钟可以发送\n" +
                "        if tonumber(oldPre) <=2 and (tonumber(newSuf) - tonumber(oldSuf)) >= 60\n" +
                "            then\n" +
                "            local newValue = tostring(-0)..'.'..tostring(oldSuf)\n" +
                "            redis.call(\"incrbyfloat\",sms_send_key,sms_send_value)\n" +
                "            redis.call(\"incrbyfloat\",sms_send_key,tonumber(newValue))\n" +
                "            return true;\n" +
                "        else\n" +
                "            return false;\n" +
                "        end\n" +
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

