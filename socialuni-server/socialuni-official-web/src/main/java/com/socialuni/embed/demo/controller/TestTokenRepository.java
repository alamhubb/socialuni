package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.TokenDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTokenRepository extends JpaRepository<TokenDO, Integer> {

    TokenDO findByToken(String token);
}
