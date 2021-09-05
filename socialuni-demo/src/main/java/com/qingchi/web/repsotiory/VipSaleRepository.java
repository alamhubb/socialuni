package com.qingchi.web.repsotiory;


import com.qingchi.web.model.DO.shell.VipSaleDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface VipSaleRepository extends JpaRepository<VipSaleDO, Integer> {
    //查找启用的，类型相同，月数活动相等的，折扣最低的
    VipSaleDO findFirstByEnabledAndActivityTypeAndGiftTypeAndMonthNumOrderByDiscount(Boolean enabled, String gainType, String giftType, Integer monthNum);
}


