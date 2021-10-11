package com.socialuni.api.model.RO.user;


import com.socialuni.social.model.model.RO.user.UserImgRO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterUserImgRO {
    private String id;
    private String src;
    private Double aspectRatio;

    public CenterUserImgRO(UserImgRO userImgRO) {
        this.src = userImgRO.getSrc();
        this.aspectRatio = userImgRO.getAspectRatio();
    }
}
