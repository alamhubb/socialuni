package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.constant.GenderTypeQueryVO;
import com.socialuni.sdk.constant.GenderTypeVO;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.sdk.dao.store.SocialTagRedis;
import com.socialuni.sdk.logic.entity.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.sdk.logic.entity.talk.SocialHomeTalkQueryEntity;
import com.socialuni.sdk.dao.DO.circle.SocialCircleDO;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryEntity socialHomeTalkQueryEntity;
    @Resource
    private SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialTagRedis socialTagRedis;
    @Resource
    TagRepository tagRepository;


    public SocialHomeTabTalkQueryBO checkAndGetHomeTalkQueryBO(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDO mineUser) {
        SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = new SocialHomeTabTalkQueryBO();
        //talk

        //tagNames转tagIds
        List<String> tagNames = queryQO.getTagNames();
        List<Integer> tagIds = new ArrayList<>();
        if (tagNames == null) {
            tagNames = new ArrayList<>();
        }
        for (String tagName : tagNames) {
            TagDO tagDO = tagRepository.findFirstByName(tagName);
            if (tagDO == null || !tagDO.getStatus().equals(CommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(tagDO.getId());
        }
        socialHomeTabTalkQueryBO.setTagIds(tagIds);
        socialHomeTabTalkQueryBO.setHomeTabName(queryQO.getHomeTabName());
        socialHomeTabTalkQueryBO.setAdCode(queryQO.getAdCode());
        socialHomeTabTalkQueryBO.setLon(queryQO.getLon());
        socialHomeTabTalkQueryBO.setLat(queryQO.getLat());
        socialHomeTabTalkQueryBO.setMinAge(queryQO.getMinAge());
        socialHomeTabTalkQueryBO.setQueryTime(queryQO.getQueryTime());
//        socialHomeTabTalkQueryBO.setPageNum(queryQO.getPageNum());
        socialHomeTabTalkQueryBO.setMaxAge(queryQO.getMaxAge());
        socialHomeTabTalkQueryBO.setDevId(DevAccountUtils.getDevIdNotNull());

       /* String circleName = queryQO.getCircleName();
        if (TalkTabType.circle_type.equals(tabType)) {
            if (StringUtils.isNotEmpty(circleName)) {
                SocialCircleDO socialCircleDO = socialCircleRepository.findFirstByName(queryQO.getCircleName());
                socialHomeTabTalkQueryBO.setCircleId(socialCircleDO.getId());
            }
        }*/
        String queryQOGender = queryQO.getGender();

        //校验query的GenderType是否正确
        GenderTypeQueryVO genderTypeQueryVO = GenderTypeVO.GenderTypeQueryMap.get(queryQOGender);
        if (genderTypeQueryVO == null) {
            throw new SocialParamsException("错误的性别类型");
        }
        //如果为专属性别动态
        if (mineUser != null) {
            if (GenderType.onlyGenders.contains(queryQOGender)) {
//                throw new SocialParamsException("请登录后查看，查看专属性别动态");
                String mineUserGender = mineUser.getGender();
                //如果为筛选仅男生可见，且用户不为男生，则报错
                if (queryQOGender.equals(GenderType.onlyBoy) && !GenderType.boy.equals(mineUserGender)) {
                    throw new SocialParamsException("仅男生可见动态");
                } else if (queryQOGender.equals(GenderType.onlyGirl) && !GenderType.girl.equals(mineUserGender)) {
                    throw new SocialParamsException("仅女生可见动态");
                }
            }
        }
        socialHomeTabTalkQueryBO.setTalkVisibleGender(genderTypeQueryVO.getTalkVisibleGender());
        socialHomeTabTalkQueryBO.setTalkUserGender(genderTypeQueryVO.getTalkUserGender());
//        socialHomeTabTalkQueryBO.setHasPeopleImgTalkNeedIdentity(queryQO.getHasPeopleImgTalkNeedIdentity());

        return socialHomeTabTalkQueryBO;
    }

    //查询非关注tab的动态列表
    public List<SocialuniTalkRO> queryHomeTabTalks(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDO mineUser) {
        //校验gender类型,生成BO
        SocialHomeTabTalkQueryBO queryBO = this.checkAndGetHomeTalkQueryBO(queryQO, mineUser);

        //根据不同的tab区分不同的查询逻辑
        String homeTabName = queryBO.getHomeTabName();
        //得到数据库talk
        List<SocialTalkDO> talkDOS;
        if (homeTabName.equals(TalkTabType.follow_name)) {
            //查询关注的用户
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(new ArrayList<>(), mineUser);
        } else if (homeTabName.equals(TalkTabType.home_name) || homeTabName.equals(TalkTabType.city_name)) {
            //执行首页查询逻辑
            talkDOS = socialHomeTalkQueryEntity.queryHomeTalks(queryBO, mineUser);
        } else if () {

        }


        if (!TalkTabType.values.contains(homeTabName)) {
            throw new SocialParamsException("错误的tab类型");
        }
        //需要校验

        //得到数据库talk
        List<SocialTalkDO> talkDOS;
        //为关注，查询关注
        if ((TalkTabType.follow_type.equals(homeTabName))) {
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(new ArrayList<>(), mineUser);
        } else {
            talkDOS = socialHomeTalkQueryEntity.queryHomeTalks(queryBO, mineUser);
        }
        //转换为rolist
        List<SocialuniTalkRO> socialTalkROs = SocialTalkROFactory.newHomeTalkROs(mineUser, talkDOS, queryBO);
        return socialTalkROs;
    }
}
