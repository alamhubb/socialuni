package com.socialuni.social.tance.repository;

import com.socialuni.social.tance.entity.AppConfigDO;
import com.socialuni.social.tance.entity.AppConfigPk;
import com.socialuni.social.tance.sdk.api.ConfigApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppConfigRepository extends JpaRepository<AppConfigDO, AppConfigPk>, ConfigApi {
    List<AppConfigDO> findAllByStatusOrderByCreateTimeDesc(String status);
    @Override
    default   String getString(String devKey,String key){
        Optional<AppConfigDO> appConfig = this.findById(new AppConfigPk(devKey, key));
        AppConfigDO appConfigDO = appConfig.orElseThrow(() -> new NullPointerException(String.format("devKey=[%s], key=[%s]没有对应的内容",devKey, key)));
        return appConfigDO.getValue();
    }
}

