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
    private Integer userId;
    private String token;

    public TokenDO(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
