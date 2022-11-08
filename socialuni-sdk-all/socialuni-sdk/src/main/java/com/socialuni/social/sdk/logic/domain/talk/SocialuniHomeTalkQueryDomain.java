package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.community.sdk.entity.TagDO;
import com.socialuni.social.community.sdk.repository.TagRepository;
import com.socialuni.social.community.sdk.repository.TalkRepository;
import com.socialuni.social.user.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.constant.GenderTypeQueryVO;
import com.socialuni.social.sdk.constant.GenderTypeVO;
import com.socialuni.social.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.social.sdk.constant.config.SocialuniAppType;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.sdk.logic.entity.talk.SocialFollowUserTalksQueryEntity;
import com.socialuni.social.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.sdk.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.social.sdk.model.QO.talk.SocialuniHomeTabTalkQueryQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialuniHomeTalkQueryDomain {
    @Resource
    private SocialFollowUserTalksQueryEntity socialFollowUserTalksQueryEntity;
    @Resource
    TagRepository tagApi;
    @Resource
    private TalkRepository talkApi;
    @Resource
    private TalkQueryStore talkQueryStore;

    public List<SocialuniTalkRO> queryStickTalks() {
        List<?  extends SocialuniTalkDO>  list = talkApi.findTop2ByStatusAndDevIdAndGlobalTopGreaterThanOrderByGlobalTopDesc(ContentStatus.enable, DevAccountFacade.getDevIdNotNull(), SocialuniConst.initNum);
        //转换为rolist
        List<SocialuniTalkRO> socialTalkROs = SocialTalkROFactory.newHomeTalkROs(SocialuniUserUtil.getMineUserAllowNull(), list, null);
        return socialTalkROs;
    }

    //查询非关注tab的动态列表
    public List<SocialuniTalkRO> queryHomeTabTalks(SocialuniHomeTabTalkQueryQO queryQO) {
        //区分是否为自身应用，不同逻辑
        /*if (DevAccountUtils.isCenter()) {


        } else {

        }*/

        //获取当前用户
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        //校验gender类型,生成BO，包含业务逻辑
        SocialHomeTabTalkQueryBO queryBO = this.checkAndGetHomeTalkQueryBO(queryQO, mineUser);


        //根据不同的tab区分不同的查询逻辑
        String homeTabName = queryBO.getHomeTabName();

        //得到数据库talk
        List<?  extends SocialuniTalkDO>  talkDOS;
        if (homeTabName.equals(TalkTabType.follow_name)) {
            //查询关注的用户
            talkDOS = socialFollowUserTalksQueryEntity.queryUserFollowTalks(new ArrayList<>(), mineUser);
        } else {
            //支持你修改bo
            talkDOS = talkQueryStore.queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(queryBO);
        }
        //转换为rolist
        List<SocialuniTalkRO> socialTalkROs = SocialTalkROFactory.newHomeTalkROs(mineUser, talkDOS, queryBO);
        return socialTalkROs;
    }

    @Resource
    SocialuniTalkQueryGenerateQueryBOByTabDomain socialuniTalkQueryGenerateQueryBOByTabDomain;


    public SocialHomeTabTalkQueryBO checkAndGetHomeTalkQueryBO(SocialuniHomeTabTalkQueryQO queryQO, SocialuniUserDo mineUser) {
        //主要逻辑区分就是这里，
        //通用逻辑
        //普通类型的逻辑
        //同性社区
        //主要是校验appgender,只允许同性别用户使用，不同性别则要保证同性别
        if (SocialuniAppType.genderTypeList.contains(SocialuniAppConfig.getAppConfig().getAppGender())) {
            if (mineUser != null) {
                String appGender = DevAccountFacade.getAppGenderType();
                String mineUserGender = mineUser.getGender();
                //app性别为女生，且用户不为女生提示错误
                if (appGender.equals(GenderType.girl) && !mineUserGender.equals(GenderType.girl)) {
                    throw new SocialSystemException("此软件为女生专属应用");
                } else if (appGender.equals(GenderType.boy) && !mineUserGender.equals(GenderType.boy)) {
                    throw new SocialSystemException("此软件为男生专属应用");
                }
            }
        }

        //校园社区
        SocialHomeTabTalkQueryBO socialHomeTabTalkQueryBO = socialuniTalkQueryGenerateQueryBOByTabDomain.generateQueryBOByTab(queryQO, mineUser);
        //校验talk可见类型是否与appgender类型一致，还有与usergender类型一致
//        GenderUtil.checkAppAndVisibleGender(appGender, postUserGender, talkVisibleGender, mineUser);

        //通用校验
        List<String> tagNames = queryQO.getTagNames();
        List<Integer> tagIds = new ArrayList<>();
        if (tagNames == null) {
            tagNames = new ArrayList<>();
        }
        for (String tagName : tagNames) {
            TagDO TagDO = tagApi.findFirstByName(tagName);
            if (TagDO == null || !TagDO.getStatus().equals(CommonStatus.enable)) {
                throw new SocialBusinessException("选择了无效的话题");
            }
            tagIds.add(TagDO.getId());
        }
        if (tagIds.size() > 3) {
//            return new ResultRO<>("最多同时筛选3个话题");
            throw new SocialParamsException("最多同时筛选3个话题");
        }
        socialHomeTabTalkQueryBO.setTagIds(tagIds);


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

        //通用逻辑
        //年龄
        //设置极限值
        Integer minAge = -500;
        Integer frontMinAge = queryQO.getMinAge();
        //如果前台传过来的不为空则使用前台的
        if (!ObjectUtils.isEmpty(frontMinAge) && frontMinAge > 8) {
            minAge = frontMinAge;
        }
        socialHomeTabTalkQueryBO.setMinAge(minAge);
        //设置极限值
        Integer maxAge = 500;
        Integer frontMaxAge = queryQO.getMaxAge();
        //如果前台传过来的不为空则使用前台的，40为前台最大值，如果选择40则等于没设置上线
        if (!ObjectUtils.isEmpty(frontMaxAge) && frontMaxAge < 40) {
            maxAge = frontMaxAge;
        }
        socialHomeTabTalkQueryBO.setMaxAge(maxAge);
        socialHomeTabTalkQueryBO.setQueryTime(queryQO.getQueryTime());
        socialHomeTabTalkQueryBO.setDevId(DevAccountFacade.getDevIdNotNull());

        String adCode = queryQO.getAdCode();
        //如果首页，不筛选地理位置
        if (SocialuniConst.chinaDistrictCode.equals(adCode) || adCode == null) {
            adCode = null;
        } else {
            //如果为空，为0或者为中国，则查询全部
            //话题校验
            //老版本走着里没啥问题，去判断到底多少，也能为空
            int adCodeInt = Integer.parseInt(adCode);
            if (adCodeInt % 100 == 0) {
                adCodeInt = adCodeInt / 100;
                if (adCodeInt % 100 == 0) {
                    adCodeInt = adCodeInt / 100;
                }
            }
            adCode = String.valueOf(adCodeInt);
        }
        socialHomeTabTalkQueryBO.setAdCode(adCode);
        socialHomeTabTalkQueryBO.setLon(queryQO.getLon());
        socialHomeTabTalkQueryBO.setLat(queryQO.getLat());

        return socialHomeTabTalkQueryBO;
    }
}
