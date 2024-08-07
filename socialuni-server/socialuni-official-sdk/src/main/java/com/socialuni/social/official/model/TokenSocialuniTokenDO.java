package com.socialuni.social.official.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_test_token_socialuni_token")
@Data
@NoArgsConstructor
public class TokenSocialuniTokenDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tokenId;
    private String socialuniToken;

    public TokenSocialuniTokenDO(Integer tokenId, String socialuniToken) {
        this.tokenId = tokenId;
        this.socialuniToken = socialuniToken;
    }
}
