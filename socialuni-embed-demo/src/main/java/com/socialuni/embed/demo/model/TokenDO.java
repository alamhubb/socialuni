package com.socialuni.embed.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_token")
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
