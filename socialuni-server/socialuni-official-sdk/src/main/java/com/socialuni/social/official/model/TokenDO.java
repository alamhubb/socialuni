package com.socialuni.social.official.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_test_token")
@Data
@NoArgsConstructor
public class TokenDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private String token;

    public TokenDO(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
