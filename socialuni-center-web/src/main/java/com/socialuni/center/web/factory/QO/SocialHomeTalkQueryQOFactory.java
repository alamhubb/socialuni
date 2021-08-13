package com.socialuni.center.web.factory.QO;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2021-08-06 20:47
 */
public class SocialHomeTalkQueryQOFactory {
    public static SocialHomeTabTalkQueryQO getTalkQueryQO(CenterHomeTabTalkQueryQO queryQO) {
        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = new SocialHomeTabTalkQueryQO();

        List<Integer> talkIds = UnionIdDbUtil.getContentIdsByTalkUnionIds(queryQO.getTalkIds());

         /*private List<Integer> talkIds;
        private String tabType;
        private List<Integer> tagIds;
        private String adCode;
        private Double lon;
        private Double lat;
        private Integer minAge;
        private Integer maxAge;
        private String userGender;
        private String talkVisibleGender;
        private String genderType;
        private Boolean openPosition;
        private String platform;
        private String standby;*/

        socialHomeTabTalkQueryQO.setTalkIds(talkIds);
        socialHomeTabTalkQueryQO.setTabType(queryQO.getTabType());
        socialHomeTabTalkQueryQO.setTagIds(queryQO.getTagIds());
        socialHomeTabTalkQueryQO.setAdCode(queryQO.getAdCode());
        socialHomeTabTalkQueryQO.setLat(queryQO.getLat());
        socialHomeTabTalkQueryQO.setLon(queryQO.getLon());
        socialHomeTabTalkQueryQO.setGenderType(queryQO.getGenderType());
        socialHomeTabTalkQueryQO.setMinAge(queryQO.getMinAge());
        socialHomeTabTalkQueryQO.setMaxAge(queryQO.getMaxAge());
        socialHomeTabTalkQueryQO.setUserGender(queryQO.getUserGender());
        socialHomeTabTalkQueryQO.setTalkVisibleGender(queryQO.getTalkVisibleGender());
        socialHomeTabTalkQueryQO.setOpenPosition(queryQO.getOpenPosition());
        socialHomeTabTalkQueryQO.setPlatform(queryQO.getPlatform());
        socialHomeTabTalkQueryQO.setStandby(queryQO.getStandby());

        return socialHomeTabTalkQueryQO;
    }
}
