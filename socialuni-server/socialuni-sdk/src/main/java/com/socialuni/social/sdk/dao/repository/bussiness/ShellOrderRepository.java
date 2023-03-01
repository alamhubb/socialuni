package com.socialuni.social.sdk.dao.repository.bussiness;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.sdk.constant.shell.ShellOrderType;
import com.socialuni.social.sdk.dao.DO.bussiness.ShellOrderDO;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ShellOrderRepository extends JpaRepository<ShellOrderDO, Integer> {
    //    VipOrderDO findFirstByEnabledAndUserOrderByIdDesc(Boolean enabled, Integer userId);
    //支付成功后需要修改用户拥有贝壳
    List<ShellOrderDO> findAllByUserIdAndStatusOrderByCreateTimeDesc(Integer userId, String status);
}
