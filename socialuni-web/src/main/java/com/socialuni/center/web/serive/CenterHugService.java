package com.socialuni.center.web.serive;


import com.socialuni.api.model.QO.CenterHugAddQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.sdk.domain.hug.SocialAddHugDomain;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.model.model.RO.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterHugService {
    @Resource
    private SocialAddHugDomain socialAddHugDomain;

    public ResultRO<Void> addHug(CenterHugAddQO centerHugAddQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();

        Integer talkId = UnionIdDbUtil.getTalkIdByUnionId(centerHugAddQO.getTalkId());

        socialAddHugDomain.addHug(mineUser, new SocialHugAddQO(talkId, null));
        return new ResultRO<>();
    }
}
