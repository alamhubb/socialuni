package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.constant.GenderTypeQueryVO;
import com.socialuni.sdk.constant.GenderTypeVO;
import com.socialuni.sdk.constant.TalkTabType;
import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.store.SocialTagRedis;
import com.socialuni.sdk.entity.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.sdk.entity.talk.SocialHomeTalkQueryEntity;
import com.socialuni.sdk.model.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.DO.tag.TagDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.repository.community.SocialCircleRepository;
import com.socialuni.sdk.repository.community.TagRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.sdk.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
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


    public SocialHomeTabTalkQueryBO checkAndGetHomeTalkQueryBO(SocialHomeTabTalkQueryQO queryQO, SocialUserDO mineUser) {
        SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = new SocialHomeTabTalkQueryBO();
        //talk

        //tagNames转tagIds
        List<Integer> tagIds = queryQO.getTagIds();
        if (tagIds == null) {
            tagIds = new ArrayList<>();
            List<String> tagNames = queryQO.getTagNames();
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
            queryQO.setTagIds(tagIds);
        }
        String tabType = queryQO.getHomeTabType();
        socialHomeTabTalkQueryBO.setHomeTabType(tabType);
        socialHomeTabTalkQueryBO.setAdCode(queryQO.getAdCode());
        socialHomeTabTalkQueryBO.setLon(queryQO.getLon());
        socialHomeTabTalkQueryBO.setLat(queryQO.getLat());
        socialHomeTabTalkQueryBO.setMinAge(queryQO.getMinAge());
        socialHomeTabTalkQueryBO.setQueryTime(queryQO.getQueryTime());
        socialHomeTabTalkQueryBO.setMaxAge(queryQO.getMaxAge());
        socialHomeTabTalkQueryBO.setDevId(DevAccountUtils.getDevIdNotNull());

        String circleName = queryQO.getCircleName();
        if (TalkTabType.circle_type.equals(tabType)) {
            if (StringUtils.isNotEmpty(circleName)) {
                SocialCircleDO socialCircleDO = socialCircleRepository.findFirstByName(queryQO.getCircleName());
                socialHomeTabTalkQueryBO.setCircleId(socialCircleDO.getId());
            }
        }
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
        socialHomeTabTalkQueryBO.setHasPeopleImgTalkNeedIdentity(queryQO.getHasPeopleImgTalkNeedIdentity());

        return socialHomeTabTalkQueryBO;
    }

    //查询非关注tab的动态列表
    public List<SocialTalkRO> queryHomeTabTalks(SocialHomeTabTalkQueryQO queryQO, SocialUserDO mineUser) {
        //校验gender类型,生成BO
        SocialHomeTabTalkQueryBO queryBO = this.checkAndGetHomeTalkQueryBO(queryQO, mineUser);

        String tabType = queryBO.getHomeTabType();
        if (!TalkTabType.values.contains(tabType)) {
            throw new SocialParamsException("错误的tab类型");
        }
        //得到数据库talk
        List<SocialTalkDO> talkDOS;
        //为关注，查询关注
        if ((TalkTabType.follow_type.equals(tabType))) {
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(new ArrayList<>(), mineUser);
        } else {
            talkDOS = socialHomeTalkQueryEntity.queryHomeTalks(queryBO, mineUser);
        }
        //转换为rolist
        List<SocialTalkRO> socialTalkROs = SocialTalkROFactory.newHomeTalkROs(mineUser, talkDOS, queryBO);
        return socialTalkROs;
    }
}