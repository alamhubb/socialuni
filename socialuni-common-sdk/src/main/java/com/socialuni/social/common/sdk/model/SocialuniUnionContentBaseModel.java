package com.socialuni.social.common.sdk.model;

import com.socialuni.social.common.sdk.model.SocialuniContentBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniUnionContentBaseModel extends SocialuniContentBaseModel implements Serializable {
    private Integer userId;
    private String contentType;
    private Integer unionId;
    //内容主体
    private String content;
    /**
     * 举报次数
     */
    private Integer reportNum;

    //违规类型
    private String violateType;
    /**
     * 删除原因
     */
    private String deleteReason;

    public SocialuniUnionContentBaseModel(Integer userId, String contentType, String content) {
        super(userId);
        this.reportNum = 0;
        this.contentType = contentType;
        this.content = content;
    }
}