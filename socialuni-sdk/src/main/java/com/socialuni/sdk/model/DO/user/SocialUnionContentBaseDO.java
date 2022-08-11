package com.socialuni.sdk.model.DO.user;

import com.socialuni.sdk.model.DO.SocialContentBaseDO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@MappedSuperclass
@Data
public class SocialUnionContentBaseDO extends SocialContentBaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer unionId;

    public Integer getUnionId() {
//        throw new SocialParamsException("内容id字段不可使用，请使用unionId");
        return this.unionId;
    }
}