package com.socialuni.social.official.logic;


import com.socialuni.social.official.model.TokenSocialuniTokenDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTokenSocialuniTokenRepository extends JpaRepository<TokenSocialuniTokenDO, Integer> {

    TokenSocialuniTokenDO findByTokenId(Integer tokenId);
}
