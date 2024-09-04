package com.socialuni.social.sdk.dao.DO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//联盟里其他app已注册的本应用里的用户
@Entity
@Table(name = "s_open_out_register_user",
        uniqueConstraints = {
                //一个人只能关注另一个人一次
                @UniqueConstraint(columnNames = {"devId", "userId"}),
        }
)
@Data
@NoArgsConstructor
public class UniOutRegisterUserDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer devId;
    private Long userId;

    public UniOutRegisterUserDO(Integer devId, Long userId) {
        this.devId = devId;
        this.userId = userId;
    }
}
