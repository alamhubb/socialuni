package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.factory.QO.SocialHomeTalkQueryQOFactory;
import com.socialuni.sdk.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.sdk.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.exception.SocialSystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Component
@Slf4j
public class CenterHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTabTalks(@Valid CenterHomeTabTalkQueryQO queryQO) {
        Integer pageNum = queryQO.getPageNum();
        if (pageNum == null) {
            if (queryQO.getTalkIds() != null) {
                pageNum = queryQO.getTalkIds().size() / 10;
                queryQO.setPageNum(pageNum);
            }
        }

        //获取当前用户
        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = SocialHomeTalkQueryQOFactory.getTalkQueryQO(queryQO);

        //校验talk可见类型是否与appgender类型一致，还有与usergender类型一致
//        GenderUtil.checkAppAndVisibleGender(appGender, postUserGender, talkVisibleGender, mineUser);

        //主要是校验appgender,只允许同性别用户使用，不同性别则要保证同性别
        if (mineUser != null) {
            String appGender = DevAccountUtils.getAppGenderType();
            String mineUserGender = mineUser.getGender();
            //app性别为女生，且用户不为女生提示错误
            if (appGender.equals(GenderType.girl) && !mineUserGender.equals(GenderType.girl)) {
                throw new SocialSystemException("此软件为女生专属应用");
            } else if (appGender.equals(GenderType.boy) && !mineUserGender.equals(GenderType.boy)) {
                throw new SocialSystemException("此软件为男生专属应用");
            }
        }

        //转换为rolist
        List<SocialTalkRO> socialTalkROs = socialHomeTalkQueryDomain.queryHomeTabTalks(socialHomeTabTalkQueryQO, mineUser);

        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(socialTalkROs, mineUser);
        return talkROS;
    }
}