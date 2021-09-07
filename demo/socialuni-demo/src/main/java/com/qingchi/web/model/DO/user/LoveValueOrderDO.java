package com.qingchi.web.model.DO.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-03-10 20:41
 * <p>
 * 爱心值，每次看广告增加爱心值，奉献对软件的爱心
 */@Entity
@Table(name = "love_value_order")
@Data
public class LoveValueOrderDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer loveValue;
    //视频是否是在用户完整观看的情况下被关闭的
    private Boolean enable;
    /**
     * 每天只有前三次观看才能获得爱心值
     */
    private Boolean award;
    private String status;

    //邀请你的用户
    private Integer userId;
}
