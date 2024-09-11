package com.socialuni.social.tance.dev.dao.repository;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.tance.dev.dao.DO.AppConfigDO;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
public interface AppConfigRepository extends JpaRepository<AppConfigDO, Integer> {
    public static final Integer DEFAULT_DEV_KEY = 0;

    @Cacheable(value = "getDevAppConfigs", key = "#devId")
    @Query("select s.id from AppConfigDO s where s.devId = :devId and s.status =:status")
    List<Integer> findAllByDevIdAndStatusOrderByCreateTimeDesc(Integer devId, Integer status);


//    @Cacheable(value = "findIdByDevIdAndConfigKey", key = "#devId+'-'+#configKey")
    @Query("select s.id from AppConfigDO s where s.devId = :devId and s.configKey =:configKey")
    Integer findIdByDevIdAndConfigKey(Integer devId, String configKey);

    @Cacheable(value = "findAppConfigDOFirstById", key = "#id")
    AppConfigDO findFirstById(Integer id);

    @Caching(
            evict = {
                    //用户的talks肯定变化了
                    //新增一条数据肯定所有数据清空，数据的显示数据变了
                    @CacheEvict(cacheNames = "getDevAppConfigs", allEntries = true),
                    //新增一条数据肯定所有数据清空，数据的显示数据变了
                    @CacheEvict(cacheNames = "findIdByDevIdAndConfigKey", key = "#appConfigDO.devId+'-'+#appConfigDO.configKey")
            },
            put = {
                    @CachePut(cacheNames = "findAppConfigDOFirstById", key = "#appConfigDO.id")
            }
    )
    default AppConfigDO savePut(AppConfigDO appConfigDO) {
        return this.save(appConfigDO);
    }

/*    @Override
    default String getString( String key) {
        // 先从缓存里面找。没有再找下面的逻辑。
        HttpServletRequest request = RequestUtil.getRequest();
        if(request != null){
            Map<String, Object> allConfigsOfMap = (Map<String, Object>) request.getAttribute(SocialSystemConst.CONFIGS_REQUEST_NAME);
            Object cacheObj = allConfigsOfMap.get(key);
            if(cacheObj != null){
                return String.valueOf(cacheObj);
            }
        }
        Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();
        Optional<AppConfigDO> appConfig = this.findById(new AppConfigPk(devId, key));
        // 没有就找默认的联盟的key。 还没有就报错啦。
        AppConfigDO appConfigDO = appConfig.orElseGet(() -> this.findById(new AppConfigPk(DEFAULT_DEV_KEY, key)).orElseThrow(() -> new NullPointerException(String.format("devKey=[%s], key=[%s]没有对应的默认内容", devId, key))));
        return appConfigDO.getValue();
    }*/
}

