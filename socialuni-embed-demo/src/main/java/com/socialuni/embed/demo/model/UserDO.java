package com.socialuni.embed.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_user",
        uniqueConstraints = {
                //不允许不同应用重复写入一条内容
                @UniqueConstraint(columnNames = {"name"}),
        }
)
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
