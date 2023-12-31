package com.socialuni.social.admin.model;/*
package com.socialuni.admin.web.model;

import com.qingchi.base.model.user.UserImgDO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserImgVO {
    private Integer id;
    private String src;
    private Double aspectRatio;

    public UserImgVO() {
    }

    public UserImgVO(UserImgDO img) {
        this.id = img.getId();
        this.src = img.getSrc();
        this.aspectRatio = img.getAspectRatio();
    }

    public static List<UserImgVO> userImgDOToVOS(List<UserImgDO> imgDOs) {
        return imgDOs.stream().map(UserImgVO::new).collect(Collectors.toList());
    }
}
*/
