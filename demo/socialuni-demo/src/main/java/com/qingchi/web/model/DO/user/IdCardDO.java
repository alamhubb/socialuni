package com.qingchi.web.model.DO.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-08-28 0:04
 */
@Data
@Entity
@Table(name = "user_id_card")
public class IdCardDO implements Serializable {
    //userid,正面反面，照片名称
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //没有这行 级联保存不生效
    private Integer userId;
    //正面身份证地址
    private String frontIdCardImg;
    //反面身份证地址
    private String backIdCardImg;
    private Date createDate;
    private Date updateDate;
    //认证中，已认证
    //目前这个属性没有作用，只维护用户UserDO的idCardStatus属性
    private String status;
}