package com.socialuni.embed.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "s_test_user")
@Data
@NoArgsConstructor
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public UserDO(String name) {
        this.name = name;
    }
}
