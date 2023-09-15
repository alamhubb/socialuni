package com.socialuni.social.peiwan.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniImgBaseDo;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Data
public class SocialuniPeiwanInfoImgDO extends SocialuniUnionContentBaseDO {

    //图片大小
    Integer level;
    Integer fadsf;

    public SocialuniPeiwanInfoImgDO() {
    }


    public SocialuniPeiwanInfoImgDO(Integer userId, Integer unionId, String contentType) {
        super(userId, unionId, contentType);
    }
}
