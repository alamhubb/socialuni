package com.socialuni.sdk.model.RO.match;

import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.RO.UserImgVO;
import com.socialuni.sdk.utils.model.DO.UserImgDOUtils;
import com.socialuni.sdk.model.RO.app.SocialDistrictRO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class MatchUserVO {
    private String id;
    private String nickname;
    private String gender;
    private Integer age;
    private String location;
    private String birthday;
    private String idCardStatus;
    //显示用户颜值，被查看的时候不更新分数，只有被喜欢的时候才更新分数
    private Integer faceRatio;
    private Integer likeCount;
    private List<UserImgVO> imgs;

    private Boolean onlineFlag;
    //用户最后在线时间，
    private Date lastOnlineTime;
    //是否为vip，
    private Boolean vipFlag;
    //已开vip多少个月
    private Boolean yearVipFlag;
    private Double distance;
    /**
     * user的地理位置，先不展示这两个
     */
    private SocialDistrictRO district;


    public MatchUserVO() {
    }

    public MatchUserVO(SocialuniUserDO user, SocialuniUserDO sessionUser) {
        if (user != null) {
            this.id = UnionIdDbUtil.getUidByUnionIdNotNull(user.getUnionId());
            this.nickname = StringUtils.substring(user.getNickname(), 0, 6);
            this.gender = user.getGender();
//            this.location = user.getLocation();
            this.age = user.getAge();
            this.birthday = user.getBirthday();
//            this.idCardStatus = user.getIdCardStatus();
            //满分10W /1千，得到百分之颜值分
//            this.faceRatio = (int) Math.ceil((double) user.getFaceRatio() / MatchConstants.FACE_RATIO_BASE_MULTIPLE);
//            this.likeCount = user.getLikeCount();
            List<SocialUserImgDO> userImgDOS = UserImgDOUtils.getImgs(user.getUnionId());
//            this.imgs = UserImgBO.userImgDOToVOS(userImgDOS, sessionUser);
//            this.onlineFlag = user.getOnlineFlag();
//            this.lastOnlineTime = user.getLastOnlineTime();
//            this.vipFlag = user.getVipFlag();
//            this.yearVipFlag = user.getYearVipFlag();
        }
    }

    public static List<MatchUserVO> userDOToVOS(List<SocialuniUserDO> userDOs, SocialuniUserDO user) {
        return userDOs.stream().map(item -> new MatchUserVO(item, user)).collect(Collectors.toList());
    }

}
