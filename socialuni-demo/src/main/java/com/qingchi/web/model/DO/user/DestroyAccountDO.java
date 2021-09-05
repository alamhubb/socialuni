package com.qingchi.web.model.DO.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
//注销了的账号列表
@Entity
@Table(name = "destroy_account")
@Data
public class DestroyAccountDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;

    private Date endTime;

    private Integer userId;

    private String status;
}
