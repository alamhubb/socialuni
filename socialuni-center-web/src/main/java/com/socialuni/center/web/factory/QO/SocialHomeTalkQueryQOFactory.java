package com.socialuni.center.web.factory.QO;

import com.socialuni.center.web.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.center.web.model.QO.talk.CenterHomeTabTalkQueryQO;

/**
 * @author qinkaiyuan
 * @date 2021-08-06 20:47
 */
public class SocialHomeTalkQueryQOFactory {
    public static SocialHomeTabTalkQueryQO getTalkQueryQO(CenterHomeTabTalkQueryQO queryQO) {
        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = new SocialHomeTabTalkQueryQO();

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

        socialHomeTabTalkQueryQO.setHomeTabType(queryQO.getHomeTabType());
        socialHomeTabTalkQueryQO.setTagIds(queryQO.getTagIds());
        socialHomeTabTalkQueryQO.setTagNames(queryQO.getTagNames());
        socialHomeTabTalkQueryQO.setGender(queryQO.getGender());
        socialHomeTabTalkQueryQO.setAdCode(queryQO.getAdCode());
        socialHomeTabTalkQueryQO.setLat(queryQO.getLat());
        socialHomeTabTalkQueryQO.setLon(queryQO.getLon());
        socialHomeTabTalkQueryQO.setMinAge(queryQO.getMinAge());
        socialHomeTabTalkQueryQO.setMaxAge(queryQO.getMaxAge());
        socialHomeTabTalkQueryQO.setCircleName(queryQO.getCircleName());
        socialHomeTabTalkQueryQO.setQueryTime(queryQO.getQueryTime());
        if (socialHomeTabTalkQueryQO.getQueryTime() == null) {
            socialHomeTabTalkQueryQO.setQueryTime(queryQO.getQueryDate());
        }
        socialHomeTabTalkQueryQO.setHasPeopleImgTalkNeedIdentity(queryQO.getHasPeopleImgTalkNeedIdentity());
//        socialHomeTabTalkQueryQO.setUserGender(queryQO.getUserGender());
//        socialHomeTabTalkQueryQO.setTalkVisibleGender(queryQO.getTalkVisibleGender());
//        socialHomeTabTalkQueryQO.setOpenPosition(queryQO.getOpenPosition());
//        socialHomeTabTalkQueryQO.setPlatform(queryQO.getPlatform());
//        socialHomeTabTalkQueryQO.setStandby(queryQO.getStandby());
        return socialHomeTabTalkQueryQO;
    }
}
