package com.qingchi.web.repsotiory;

import com.qingchi.web.model.DO.shell.ShellOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface ShellOrderRepository extends JpaRepository<ShellOrderDO, Integer> {
    //    VipOrderDO findFirstByEnabledAndUserOrderByIdDesc(Boolean enabled, Integer userId);
    //支付成功后需要修改用户拥有贝壳
    List<ShellOrderDO> findAllByUserIdAndStatusOrderByCreateTimeDesc(Integer userId, String status);
}


