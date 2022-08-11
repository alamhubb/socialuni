package com.socialuni.sdk.serive;


import com.socialuni.sdk.model.QO.CenterHugAddQO;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.domain.hug.SocialAddHugDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(CenterHugAddQO centerHugAddQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer talkId = UnionIdDbUtil.getTalkUnionIdByUidNotNull(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
