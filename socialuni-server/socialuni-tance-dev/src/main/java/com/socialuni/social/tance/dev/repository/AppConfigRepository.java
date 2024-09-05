package com.socialuni.social.tance.dev.repository;

import com.socialuni.social.common.api.constant.SocialSystemConst;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.tance.dev.model.DO.AppConfigDO;
import com.socialuni.social.tance.dev.model.DO.AppConfigPk;
import com.socialuni.social.tance.dev.api.ConfigInterface;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
public interface AppConfigRepository extends JpaRepository<AppConfigDO, AppConfigPk>, ConfigInterface {
    public static final Integer DEFAULT_DEV_KEY = 0;

    @Cacheable(value = "getDevAppConfigs", key = "#devId")
    List<AppConfigDO> findAllByDevIdAndStatusOrderByCreateTimeDesc(Integer devId, Integer status);

    @Override
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
    }
}

