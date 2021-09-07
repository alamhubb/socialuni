package com.qingchi.web.store;

import com.qingchi.web.model.DO.shell.ShellOrderDO;
import com.qingchi.web.repsotiory.ShellOrderRepository;
import com.socialuni.social.sdk.redis.redisKey.RedisKeysConst;
import com.socialuni.social.sdk.utils.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShellOrderStore {
    @Resource
    private ShellOrderRepository shellOrderRepository;
    @Resource
    private RedisUtil redisUtil;

    public List<ShellOrderDO> saveAll(List<ShellOrderDO> shellOrderDOS) {
        shellOrderDOS = shellOrderRepository.saveAll(shellOrderDOS);

        Set<String> redisKeys = shellOrderDOS.stream()
                .map((item -> RedisKeysConst.userById + RedisKeysConst.springKey + item.getUserId())).collect(Collectors.toSet());
        //清除缓存
        redisUtil.del(redisKeys);
        return shellOrderDOS;
    }
}