package com.socialuni.center.web.factory.RO.talk;

import com.socialuni.center.web.model.RO.user.CenterContentUserRO;
import com.socialuni.center.web.factory.RO.user.CenterContentUserROFactory;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.model.RO.talk.CenterTalkImgRO;
import com.socialuni.center.web.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
import com.socialuni.center.web.utils.common.BirthdayAgeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
@Component
@Slf4j
public class CenterTalkROFactory {
    //需要user因为，user需要外部传入，区分center和social
    //用户详情
    public static CenterTalkRO getTalkRO(SocialTalkRO talkRO, UserDO mineUser) {
        Integer talkId = UnionIdDbUtil.createTalkUid(talkRO);

        CenterContentUserRO centerTalkUserRO = CenterContentUserROFactory.getContentUserRO(talkRO.getUser(), mineUser);
        List<CenterTalkImgRO> imgs = CenterTalkImgROFactory.getHomeTalkImgROS(talkRO.getImgs());

        List<CenterCommentRO> comments = CenterCommentROFactory.getCommentROs(talkRO.getComments(), mineUser);

        CenterTalkRO centerTalkRO = new CenterTalkRO();

        centerTalkRO.setId(talkId);
        centerTalkRO.setUser(centerTalkUserRO);
        centerTalkRO.setImgs(imgs);
        centerTalkRO.setComments(comments);

        centerTalkRO.setContent(BirthdayAgeUtil.replaceAgeBetween10to18Str(talkRO.getContent()));
        centerTalkRO.setVisibleGender(talkRO.getVisibleGender());
        centerTalkRO.setUpdateTime(talkRO.getUpdateTime());
        centerTalkRO.setCommentNum(talkRO.getCommentNum());
        centerTalkRO.setReportNum(0);
        centerTalkRO.setHugNum(talkRO.getHugNum());
        centerTalkRO.setHasHugged(talkRO.getHasHugged());
        centerTalkRO.setHasFollowed(talkRO.getHasFollowed());
        centerTalkRO.setDistrict(talkRO.getDistrict());
        centerTalkRO.setProvinceName(talkRO.getProvinceName());
        centerTalkRO.setCityName(talkRO.getCityName());
        centerTalkRO.setDistrictName(talkRO.getDistrictName());
        centerTalkRO.setAdCode(talkRO.getAdCode());

        centerTalkRO.setTags(talkRO.getTags());
        centerTalkRO.setCircles(talkRO.getCircles());
        centerTalkRO.setDistance(talkRO.getDistance());
        centerTalkRO.setThreeAppName(talkRO.getThreeAppName());
        centerTalkRO.setThreeMpQqAppId(talkRO.getThreeMpQqAppId());
        centerTalkRO.setThreeMpWxAppId(talkRO.getThreeMpWxAppId());
        centerTalkRO.setThreeAppId(talkRO.getThreeAppId());
        centerTalkRO.setThreeId(talkRO.getThreeId());
        centerTalkRO.setThreeTalkPath(talkRO.getThreeTalkPath());
        centerTalkRO.setThreeContent(talkRO.getThreeContent());

        return centerTalkRO;
    }

    public static List<CenterTalkRO> getTalkROS(List<SocialTalkRO> ROS, UserDO mineUser) {
        return ListConvertUtil.toList(CenterTalkROFactory::getTalkRO, ROS, mineUser);
    }
}