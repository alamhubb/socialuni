package com.socialuni.center.qingchi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QingchiTokenRepository extends JpaRepository<QingchiTokenDO, Integer> {
    QingchiTokenDO findOneByToken(String token);
}