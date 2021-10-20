package com.socialuni.social.sdk.manage.circle;

import com.socialuni.social.entity.model.DO.circle.CircleDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import org.springframework.stereotype.Component;

@Component
public class CircleManage {
    //创建群聊
    public void createCircle(UserDO mineUser, CircleCreateQO circleCreateQO) {

        CircleDO circleDO = new CircleDO();
        circleDO.setName(ci);
    }
}
