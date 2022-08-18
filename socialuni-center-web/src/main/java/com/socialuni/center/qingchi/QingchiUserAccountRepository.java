package com.socialuni.center.qingchi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QingchiUserAccountRepository extends JpaRepository<QingchiUserAccountDO, Integer> {
    QingchiUserAccountDO findOneByUserIdAndProvider(Integer userId, String provider);
}