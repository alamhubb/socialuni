package com.qingchi.web.repsotiory;


import com.qingchi.web.model.DO.shell.QcbOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author qinkaiyuan
 * @date 2018-10-17 21:59
 */
public interface QcbOrderRepository extends JpaRepository<QcbOrderDO, Integer> {
}


