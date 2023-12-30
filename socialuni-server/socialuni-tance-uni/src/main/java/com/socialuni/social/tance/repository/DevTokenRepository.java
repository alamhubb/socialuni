package com.socialuni.social.tance.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.tance.entity.DevTokenDo;
import com.socialuni.social.tance.sdk.api.DevTokenInterface;
import com.socialuni.social.tance.sdk.model.DevTokenModler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/9/30 14:41
 * @since 1.0
 */
public interface DevTokenRepository extends JpaRepository<DevTokenDo, Integer> , DevTokenInterface {
    @Query(nativeQuery = true, value = "select t.token_code from s_dev_token t where t.dev_id =:devId order by id desc limit 1")
    String findFirstTokenCodeByUserId(@Param("devId") Integer devId);

    //
    @Override
    default DevTokenModler savePut(DevTokenModler devAccountModel){
        DevTokenDo devAccountDo = BeanUtil.copyProperties(devAccountModel, DevTokenDo.class);
        return this.save(devAccountDo);
    }

}
