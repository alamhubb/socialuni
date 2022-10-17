package com.socialuni.social.tance.repository;

import com.socialuni.social.tance.entity.AppConfigDO;
import com.socialuni.social.tance.entity.AppConfigPk;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppConfigRepository extends JpaRepository<AppConfigDO, AppConfigPk>, ConfigApi {
    public static final String DEFAULT_DEV_KEY = "uni-key";

//    List<AppConfigDO> findAllByStatusOrderByCreateTimeDesc(String status);

    @Override
    default String getString(Integer devId, String key) {
        Optional<AppConfigDO> appConfig = this.findById(new AppConfigPk(devId, key));
        // 没有就找默认的联盟的key。 还没有就报错啦。
        AppConfigDO appConfigDO = appConfig.orElseGet(() -> this.findById(new AppConfigPk(devId, key)).orElseThrow(() -> new NullPointerException(String.format("devKey=[%s], key=[%s]没有对应的默认内容", devId, key))));
        return appConfigDO.getValue();
    }
}

