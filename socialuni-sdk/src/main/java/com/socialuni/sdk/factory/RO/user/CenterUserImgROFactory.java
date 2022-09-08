package com.socialuni.sdk.factory.RO.user;


import com.socialuni.sdk.model.RO.user.CenterUserImgRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.RO.user.UserImgRO;

import java.util.List;

public class CenterUserImgROFactory {
    public static CenterUserImgRO getUserImgRO(UserImgRO userImg, SocialuniUserDO mineUser) {
        CenterUserImgRO imgVO = new CenterUserImgRO(userImg);
        String uid = UnionIdDbUtil.getUidByUnionIdNotNull(userImg.getId());
        imgVO.setId(uid);
        return imgVO;
    }

    public static List<CenterUserImgRO> getImgs(List<UserImgRO> imgDOs, SocialuniUserDO mineUser) {
        return ListConvertUtil.toList(CenterUserImgROFactory::getUserImgRO, imgDOs, mineUser);
    }
}
