package com.socialuni.center.web.factory.RO.user;


import com.socialuni.api.model.RO.user.CenterUserImgRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.RO.user.UserImgRO;

import java.util.List;

public class CenterUserImgROFactory {
    public static CenterUserImgRO getUserImgRO(UserImgRO userImg, UserDO mineUser) {
        CenterUserImgRO imgVO = new CenterUserImgRO(userImg);
        String uid = UnionIdDbUtil.createUserImgUid(userImg.getId(), mineUser);
        imgVO.setId(uid);
        return imgVO;
    }

    public static List<CenterUserImgRO> getImgs(List<UserImgRO> imgDOs, UserDO mineUser) {
        return ListConvertUtil.toList(CenterUserImgROFactory::getUserImgRO, imgDOs, mineUser);
    }
}
