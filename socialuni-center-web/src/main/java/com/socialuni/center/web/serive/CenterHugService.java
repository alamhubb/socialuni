package com.socialuni.center.web.serive;


import com.socialuni.center.web.model.QO.CenterHugAddQO;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.domain.hug.SocialAddHugDomain;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.community.SocialHugAddQO;
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
