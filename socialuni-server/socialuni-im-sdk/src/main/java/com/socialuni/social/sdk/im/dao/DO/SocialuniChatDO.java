package com.socialuni.social.sdk.im.dao.DO;


import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.sdk.im.enumeration.ChatOpenType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
@Entity
@Data
@Table(name = "s_im_chat", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"chatName", "type"}),
},
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "status"),
                @Index(columnList = "domainType"),
                @Index(columnList = "topLevel"),
                @Index(columnList = "type"),
        }
)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SocialuniChatDO extends SocialuniUnionContentBaseDO implements Serializable {
//    public void setUnionId(Long id){
//
//    }

    private String chatName;
    private String avatar;

    //置顶标识
//    private Boolean topFlag;
//    private String lastContent;
    //同为置顶时，区分级别
    private Integer topLevel;
    //开放类型，默认是open
    private String openType;
    //业务类型
    private String domainType;

    public SocialuniChatDO(String type) {
        this.setType(type);
        this.openType = ChatOpenType.openApply;
    }
}
