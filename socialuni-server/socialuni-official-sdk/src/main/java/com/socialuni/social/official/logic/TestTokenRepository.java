package com.socialuni.social.official.logic;


import com.socialuni.social.official.model.TokenDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTokenRepository extends JpaRepository<TokenDO, Integer> {

    TokenDO findByToken(String token);
}
