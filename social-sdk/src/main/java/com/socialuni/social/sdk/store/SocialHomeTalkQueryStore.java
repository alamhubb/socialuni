package com.socialuni.social.sdk.store;

import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.sdk.constant.CommonConst;
import com.socialuni.social.sdk.constant.GenderType;
import com.socialuni.social.sdk.constant.TalkTabType;
import com.socialuni.entity.model.DO.talk.TalkDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.repository.TalkRepository;
import com.socialuni.social.sdk.utils.TalkStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class SocialHomeTalkQueryStore {

    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkStore talkStore;
    @Resource
    private TalkQueryStore talkQueryStore;

    public List<TalkDO> queryHomeTalks(SocialHomeTabTalkQueryQO queryQO, UserDO user) {
        String userGender = queryQO.getUserGender();
        String talkGender = queryQO.getTalkVisibleGender();
        String tabType = queryQO.getTabType();

        //        log.info("queryNotFollowTalks开始2：" + new Date().getTime() / 1000);
        //userId特殊处理
        Integer userId = null;
        String sessionUserGender = null;
        if (user != null) {
            userId = user.getId();
            sessionUserGender = user.getGender();
        }

        //sql需要，为all改为null
        if (GenderType.all.equals(userGender)) {
            userGender = null;
        }


        log.debug("开始数据库查询3：" + new Date().getTime() / 1000);
        String adCode = queryQO.getAdCode();
        //添加用户使用地区的记录，记录用户最近访问的地区
        //下一版本不再记录用户使用位置，本地记录
//            districtService.addDistrictRecord(user, adCode, TalkOperateType.talkQuery);
        //必有adCode，如果首页类型为首页，则为中国
        //如果为首页不筛选地区和tag
        //只有为新版本全国才更改为空
        //如果首页，不筛选地理位置
        if (TalkTabType.home_type.equals(tabType)) {
            adCode = null;
        } else if (TalkTabType.city_type.equals(tabType)) {
            //如果为空，为0或者为中国，则查询全部
            //话题校验
            //老版本走着里没啥问题，去判断到底多少，也能为空
            if (adCode == null || CommonConst.chinaDistrictCode.equals(adCode)) {
                adCode = null;
                //否则去重后面100的整除。like查询
            } else {
                int adCodeInt = Integer.parseInt(adCode);
                if (adCodeInt % 100 == 0) {
                    adCodeInt = adCodeInt / 100;
                    if (adCodeInt % 100 == 0) {
                        adCodeInt = adCodeInt / 100;
                    }
                }
                adCode = String.valueOf(adCodeInt);
            }
        }


        log.debug("开始数据库查询1：" + new Date().getTime() / 1000);

        //这理处理appgender和queryGender
        //如果app为单性交友


        //年龄
        //设置极限值
        Integer minAge = -500;
        Integer frontMinAge = queryQO.getMinAge();
        //如果前台传过来的不为空则使用前台的
        if (frontMinAge > 8) {
            minAge = queryQO.getMinAge();
        }
        //设置极限值
        Integer maxAge = 500;
        Integer frontMaxAge = queryQO.getMaxAge();
        //如果前台传过来的不为空则使用前台的，40为前台最大值，如果选择40则等于没设置上线
        if (!ObjectUtils.isEmpty(frontMaxAge) && frontMaxAge < 40) {
            maxAge = frontMaxAge;
        }

        //tags为0的情况
        List<Integer> tagIds = queryQO.getTagIds();

        //没选择tag的情况
        if (CollectionUtils.isEmpty(tagIds)) {
            tagIds = null;
        }

        log.debug("开始数据库查询：" + new Date().getTime() / 1000);
        List<TalkDO> talkDOS = talkQueryStore.queryTalksTop10ByGenderAgeAndLikeAdCodeAndTagIds(queryQO.getTalkIds(), userId, userGender,
                minAge, maxAge, adCode, tagIds, talkGender, sessionUserGender);
        log.debug("结束数据库查询：" + new Date().getTime() / 1000);
//        log.info("queryNotFollowTalks结束2：" + new Date().getTime() / 1000);
        return talkDOS;
    }


    /**
     * 需要对talk进行操作，必传talk不可精简
     *
     * @param talk
     * @return
     */
   /* public TalkDO updateTalkByAddComment(TalkDO talk) {
        Integer commentNum = talk.getCommentNum();
        if (commentNum == null) {
            talk.setCommentNum(1);
        } else {
            talk.setCommentNum(++commentNum);
        }
        //更新talk更新时间
        talk.setUpdateTime(new Date());

        talk = talkStore.save(talk);
        return talk;
    }*/
}
