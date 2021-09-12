package com.socialuni.demo.service;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.entity.model.DO.HomeSwiperDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import com.socialuni.social.sdk.factory.SocialHomeSwiperROFactory;
import com.socialuni.social.sdk.repository.HomeSwiperRepository;
import com.socialuni.social.sdk.service.SocialFrontLogDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class AppService {
    @Resource
    HomeSwiperRepository homeSwiperRepository;
    @Resource
    SocialFrontLogDomain socialFrontLogDomain;

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        //homeSwipers
        List<HomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusOrderByTopLevelAscIdDesc(StatusConst.enable);
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO, mineUser);
    }


}
