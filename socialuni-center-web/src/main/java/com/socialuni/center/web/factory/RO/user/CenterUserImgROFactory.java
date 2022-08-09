package com.socialuni.center.web.factory.RO.user;


import com.socialuni.center.web.model.RO.user.CenterUserImgRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.UserImgRO;

import java.util.List;

public class CenterUserImgROFactory {
    public static CenterUserImgRO getUserImgRO(UserImgRO userImg, SocialUserDO mineUser) {
        CenterUserImgRO imgVO = new CenterUserImgRO(userImg);
        String uid = UnionIdDbUtil.getUidByUnionIdNotNull(userImg.getId());
        imgVO.setId(uid);
        return imgVO;
    }

    public static List<CenterUserImgRO> getImgs(List<UserImgRO> imgDOs, SocialUserDO mineUser) {
        return ListConvertUtil.toList(CenterUserImgROFactory::getUserImgRO, imgDOs, mineUser);
    }
}
