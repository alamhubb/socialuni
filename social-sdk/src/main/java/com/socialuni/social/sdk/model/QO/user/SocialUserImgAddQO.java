package com.socialuni.social.sdk.model.QO.user;


import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.constant.ContentStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SocialUserImgAddQO {
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    //压缩率
    private Double quality;
    private Integer size;
}
