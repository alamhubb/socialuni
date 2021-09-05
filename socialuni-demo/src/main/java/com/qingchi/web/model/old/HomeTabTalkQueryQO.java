package com.qingchi.web.model.old;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-08-21 20:47
 */
@Data
@NoArgsConstructor
public class HomeTabTalkQueryQO {
    private List<String> talkIds;
    private String homeType;
    private String homeTabType;
    private List<Integer> tagIds;
    private String gender;

    private String adCode;
    private Double lon;
    private Double lat;

    private Integer minAge;
    private Integer maxAge;
    private String userGender;
    //新版本用genderType，旧版本用gender
    private String talkVisibleGender;

    public CenterHomeTabTalkQueryQO toCenterQO() {
        CenterHomeTabTalkQueryQO centerQO = new CenterHomeTabTalkQueryQO();

        centerQO.setTalkIds(this.talkIds);
        if (StringUtils.isEmpty(this.homeTabType)) {
            centerQO.setHomeTabType(this.homeType);
        } else {
            centerQO.setHomeTabType(this.homeTabType);
        }
        centerQO.setTagIds(this.tagIds);
        centerQO.setTalkIds(this.talkIds);
        centerQO.setGender(this.gender);
        centerQO.setAdCode(this.adCode);
        centerQO.setLon(this.lon);
        centerQO.setLat(this.lat);
        centerQO.setMinAge(this.minAge);
        centerQO.setMaxAge(this.maxAge);
        return centerQO;
    }


    public HomeTabTalkQueryQO(List<String> talkIds, String homeType, List<Integer> tagIds, String gender, String adCode, Double lon, Double lat, Integer minAge, Integer maxAge, String userGender, String talkVisibleGender) {

    }
}
