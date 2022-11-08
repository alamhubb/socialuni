package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTokenSocialuniTokenRepository extends JpaRepository<TokenSocialuniTokenDO, Integer> {

    TokenSocialuniTokenDO findByTokenId(Integer tokenId);
}
