package com.socialuni.social.sdk.factory;

import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryBO;
import com.socialuni.social.sdk.dao.CommentDao;
import com.socialuni.social.sdk.factory.user.base.SocialContentUserROFactory;
import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.model.RectangleVO;
import com.socialuni.social.sdk.platform.AliAPI;
import com.socialuni.social.sdk.repository.CommentRepository;
import com.socialuni.social.sdk.repository.HugRepository;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.TalkImgDOUtils;
import com.socialuni.social.sdk.utils.TalkUtils;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkDistrictRO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import com.socialuni.social.model.model.RO.user.base.SocialContentUserRO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
@Component
@Slf4j
public class SocialTalkROFactory {
    private static CommentRepository commentRepository;
    private static CommentDao commentDao;
    private static HugRepository hugRepository;

    @Resource
    public void setCommentDao(CommentDao commentDao) {
        SocialTalkROFactory.commentDao = commentDao;
    }

    @Resource
    public void setCommentRepository(CommentRepository commentRepository) {
        SocialTalkROFactory.commentRepository = commentRepository;
    }

    @Resource
    public void setHugRepository(HugRepository hugRepository) {
        SocialTalkROFactory.hugRepository = hugRepository;
    }

    /*
    private static SocialUserFansDetailRepository userFollowDetailRepository;
    private static HugRepository hugRepository;
    private static TagRepository TagRepository;
    private static TagStoreUtils tagQueryRepository;

    @Resource
    public void setUserFollowDetailRepository(SocialUserFansDetailRepository userFollowDetailRepository) {
        TalkBO.userFollowDetailRepository = userFollowDetailRepository;
    }

    @Resource
    public void setHugRepository(HugRepository hugRepository) {
        TalkBO.hugRepository = hugRepository;
    }


    @Resource
    public void setTagRepository(TagRepository tagRepository) {
        TagRepository = tagRepository;
    }

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        TalkBO.talkImgRepository = talkImgRepository;
    }

    @Resource
    public void setTagQueryRepository(TagStoreUtils tagQueryRepository) {
        TalkBO.tagQueryRepository = tagQueryRepository;
    }*/

    //需要user因为，user需要外部传入，区分center和social
    //用户详情
    public static SocialTalkRO newHomeTalkRO(UserDO mineUser, Integer talkId) {
        TalkDO talkDO = TalkUtils.get(talkId);
        return SocialTalkROFactory.newHomeTalkRO(mineUser, talkDO, false, null);
    }

    public static SocialTalkRO getTalkRO(TalkDO talkDO, UserDO mineUser) {
        return SocialTalkROFactory.newHomeTalkRO(mineUser, talkDO, false, null);
    }

    public static SocialTalkRO getTalkDetailPageTalkRO(UserDO mineUser, TalkDO talkDO, Boolean showAllComment) {
        return SocialTalkROFactory.newHomeTalkRO(mineUser, talkDO, showAllComment, null);
    }

    public static SocialTalkRO newHomeTalkRO(UserDO mineUser, TalkDO talkDO, SocialHomeTabTalkQueryBO queryVO) {
        return SocialTalkROFactory.newHomeTalkRO(mineUser, talkDO, false, queryVO);
    }


    public static List<SocialTalkRO> newHomeTalkROs(UserDO mineUser, List<TalkDO> talkDOS, SocialHomeTabTalkQueryBO queryVO) {
        return talkDOS.stream().map(talkDO -> SocialTalkROFactory.newHomeTalkRO(mineUser, talkDO, queryVO)).collect(Collectors.toList());
    }

    //talk详情
    /*public static SocialTalkRO newTalkRO( SocialTalkDO talkDO, Boolean showAllComment) {
        return TalkROFactory.newTalkRO(user, talkDO, showAllComment, null, null);
    }*/

    /**
     * @param talkDO
     * @param showAllComment 如果是详情页则需要展示所有comment
     */

    public static SocialTalkRO newHomeTalkRO(UserDO mineUser, TalkDO talkDO, Boolean showAllComment, SocialHomeTabTalkQueryBO queryVO) {
        SocialTalkRO socialTalkRO = new SocialTalkRO();

        log.debug("开始每次换砖" + new Date().getTime() / 1000);
//        Integer talkId = UnionIdDbUtil.createTalkUid(talkDO.getId(), user);
        Integer talkId = talkDO.getId();

        socialTalkRO.setId(talkId);
        UserDO talkUser = SocialUserUtil.get(talkDO.getUserId());
        SocialContentUserRO socialTalkUserRO = SocialContentUserROFactory.newContentUserRO(talkUser, mineUser);
//        socialTalkUserRO.setId(UnionIdDbUtil.createUserUid(socialTalkUserRO.getId(), user));

        socialTalkRO.setUser(socialTalkUserRO);

        socialTalkRO.setContent(talkDO.getContent());
        //70毫秒，可缓存
        List<SocialTalkImgDO> imgDOS = TalkImgDOUtils.findTop3ByTalkId(talkDO.getId());
//        List<TalkImgDO> imgDOS = talkDO.getImgs();
        if (imgDOS != null && imgDOS.size() > 0) {
            socialTalkRO.setImgs(TalkImgROFactory.newTalkImgROS(imgDOS));
        } else {
            socialTalkRO.setImgs(new ArrayList<>());
        }
        //10毫秒
        log.debug("开始查询comment" + new Date().getTime() / 1000);

        List<SocialCommentRO> socialCommentROS = SocialCommentROFactory.getTalkCommentROs(mineUser, talkId, showAllComment);
        socialTalkRO.setComments(socialCommentROS);

        log.debug("结束查询comment" + new Date().getTime() / 1000);
        socialTalkRO.setUpdateTime(talkDO.getUpdateTime());
        socialTalkRO.setCommentNum(talkDO.getCommentNum());
        socialTalkRO.setHugNum(talkDO.getHugNum());
//        socialTalkRO.setReportNum(talkDO.getReportNum());
        socialTalkRO.setGlobalTop(talkDO.getGlobalTop());
        socialTalkRO.setVisibleGender(talkDO.getVisibleGender());
        socialTalkRO.setHasFollowed(socialTalkUserRO.getHasFollowed());

        //自身三方账户相关，如果为自身的动态
        /*DevAccountDO devAccountDO = DevAccountUtils.getDevAccount();
        Integer talkDevId = talkDO.getDevId();
        //如果为自身的内容，则不需要跳转内容
        if (devAccountDO != null && talkDevId.equals(devAccountDO.getId())) {
            //是否为三方app自身的内容
            socialTalkRO.setThreeContent(false);
            //设置本方的三方id
            socialTalkRO.setThreeId(talkDO.getThreeId());
        } else {
            socialTalkRO.setThreeContent(true);
            //动态三方账户相关
            DevAccountDO talkDevAccountDO = DevAccountUtils.getDevAccount(talkDO.getDevId());
            socialTalkRO.setThreeAppName(talkDevAccountDO.getAppName());
            socialTalkRO.setThreeMpQqAppId(talkDevAccountDO.getMpQqAppId());
            socialTalkRO.setThreeMpWxAppId(talkDevAccountDO.getMpWxAppId());
            //默认微信appId
            if (ProviderType.qq.equals(RequestUtils.getProvider())) {
                socialTalkRO.setThreeAppId(talkDevAccountDO.getMpQqAppId());
            } else {
                socialTalkRO.setThreeAppId(talkDevAccountDO.getMpWxAppId());
            }
            String threeId = UnionIdDbUtil.createTalkUid(talkDO.getId(), user);

            socialTalkRO.setThreeId(threeId);

            //如果单独配置了talk的路径
            String talkPage = talkDevAccountDO.getThreeTalkPath();

            String threePath = talkDevAccountDO.getThreePath();

            socialTalkRO.setThreeTalkPath(talkDevAccountDO.getThreeTalkPath());
            //如果为空，使用统一的路径
            if (StringUtils.isNotEmpty(talkPage)) {
                //不为空使用talk单独的路径
                talkPage = talkPage + "?threeId=" + threeId;
                socialTalkRO.setThreeTalkPath(talkPage);
            } else if (StringUtils.isNotEmpty(threePath)) {
                talkPage = threePath + "?contentType=" + ContentType.talk + "&threeId=" + threeId;
                socialTalkRO.setThreeTalkPath(talkPage);
            }
        }*/

        //40毫秒
        if (mineUser != null) {
            //20毫秒
            Integer hugCount = hugRepository.countByTalkIdAndUserId(talkDO.getId(), mineUser.getId());
            if (hugCount > 0) {
                socialTalkRO.setHasHugged(true);
            }
        } else {
            socialTalkRO.setHasHugged(false);
//            socialTalkRO.setHasFollowed(false);
        }
        //60毫秒，可缓存

        SocialTalkDistrictRO district = new SocialTalkDistrictRO(talkDO.getAdCode(), talkDO.getProvinceName(), talkDO.getCityName(), talkDO.getDistrictName());

        socialTalkRO.setDistrict(district);
        //10 毫秒
//        List<TagDO> tagDOS = TagStoreUtils.getTagsByTalkId(talkDO.getId());

        //50毫秒
//        this.tags = TalkTagBO.toVOs(TalkTagBO.tagDOToVOS(tagDOS));

        //如果经纬度为空

        //计算距离
        //如果查询条件有经纬度
        //耗时60毫秒

        if (queryVO == null) {
            RectangleVO rectangleVO = AliAPI.getRectangle();
            queryVO = new SocialHomeTabTalkQueryBO();
            if (rectangleVO != null) {
                queryVO.setLat(rectangleVO.getLat());
                queryVO.setLon(rectangleVO.getLon());
            }
        }

        Double lon = queryVO.getLon();
        Double lat = queryVO.getLat();
        if (lon != null && lat != null) {
            Double dbLon = talkDO.getLon();
            Double dbLat = talkDO.getLat();
            //如果talk有记录经纬度
            if (dbLon != null) {
                //经纬度换约等于大概换算成千米，任何地点经度都大致相等
                Double talkLon = dbLon * 111;
                Double talkLat = dbLat * (Math.cos(Math.toRadians(dbLat)) * 111);
                //任何地点经度都大致相等,为111公里
                Double queryLon = lon * 111;
                //计算当前纬度，1纬度等于多少公里
                Double queryLat = lat * (Math.cos(Math.toRadians(lat)) * 111);
                //两个经纬度求差
                double lonDiffAbs = Math.abs(talkLon - queryLon);
                double latDiffAbs = Math.abs(talkLat - queryLat);
                //经纬度差勾股求距离
                Double distance = Math.sqrt(Math.pow(lonDiffAbs, 2) + Math.pow(latDiffAbs, 2));
                socialTalkRO.setDistance(distance);
            }
        }
        log.debug("一次转换完成" + new Date().getTime() / 1000);
        return socialTalkRO;
    }
}