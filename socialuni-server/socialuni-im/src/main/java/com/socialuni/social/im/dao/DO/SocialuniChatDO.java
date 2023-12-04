package com.socialuni.social.im.dao.DO;


import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.im.enumeration.ChatOpenType;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import lombok.Data;
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
        }
)
@NoArgsConstructor
public class SocialuniChatDO extends SocialuniUnionContentBaseDO implements Serializable {
    private String chatName;
    private String avatar;
    //置顶标识
//    private Boolean topFlag;
//    private String lastContent;
    //同为置顶时，区分级别
    private Integer topLevel;
    //开放类型，默认是open
    private String openType;

    public SocialuniChatDO(String type) {
        this.setType(type);
        this.openType = ChatOpenType.openApply;
    }
}
