package com.socialuni.social.tance.dev.model;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocialuniUnionIdModler extends SocialuniBaseDO implements Serializable {
    //为什么使用id，方便，可以拆分为id+类型
    private String contentType;

    private String uuid;
    //数据所属权
//    private Integer belongDevId;
    //数据来源方
    private Integer fromDevId;
    //数据所有方
    //主要是不想泄露真实id，所以用的其他id
    private Long unionId;

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public SocialuniUnionIdModler(String contentType, String uuid, Integer fromDevId) {
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.contentType = contentType;
        this.uuid = uuid;
//        this.belongDevId = belongDevId;
        this.fromDevId = fromDevId;
    }

    //三方的用户，来注册的时候，好好想想各种情况， 应该都会有dataunionid
    public SocialuniUnionIdModler(String contentType, Long unionId, Integer fromDevId) {
        //这俩字段应该用不到，只是方便统计有用，也不是读出来的数据有用，评论时需要根据这个通知到对方，
        this.contentType = contentType;
        this.unionId = unionId;
        this.uuid = String.valueOf(unionId);
//        this.belongDevId = belongDevId;
        this.fromDevId = fromDevId;
    }
}
