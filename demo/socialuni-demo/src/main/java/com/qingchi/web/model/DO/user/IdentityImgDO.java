package com.qingchi.web.model.DO.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="identity_img")
public class IdentityImgDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;

    private String src;

    private Double aspectRatio;

    private Date createTime;

    private String status;
}
