package com.socialuni.social.recharge.dao.repository;

import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShellOrderRepository extends JpaRepository<SocialuniCoinOrderDO, Integer> {
    //    VipOrderDO findFirstByEnabledAndUserOrderByIdDesc(Boolean enabled, Long userId);
    //支付成功后需要修改用户拥有金币
    List<SocialuniCoinOrderDO> findAllByUserIdAndStatusOrderByCreateTimeDesc(Long userId, String status);
}
