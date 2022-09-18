package com.socialuni.sdk.logic.factory;

import com.socialuni.sdk.constant.socialuni.SocialuniContentType;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TalkImgDOFactory {
    public static SocialuniTalkImgDO newTalkImgDO(SocialuniImgAddQO talkImgVO) {
        SocialuniTalkImgDO socialTalkImgDO = new SocialuniTalkImgDO();
        Integer talkImgUnionId = SocialuniUnionIdUtil.createTalkImgUnionId();
        socialTalkImgDO.setUnionId(talkImgUnionId);

        socialTalkImgDO.setSrc(talkImgVO.getSrc());
        socialTalkImgDO.setAspectRatio(talkImgVO.getAspectRatio());
        socialTalkImgDO.setSize(talkImgVO.getSize());
        socialTalkImgDO.setContent(talkImgVO.getContent());
        socialTalkImgDO.setQuality(talkImgVO.getQuality());

        socialTalkImgDO.setContentType(SocialuniContentType.talkImg);
        return socialTalkImgDO;
    }

    public static List<SocialuniTalkImgDO> newTalkImgDOS(List<SocialuniImgAddQO> imgVOS) {
        return imgVOS.stream().map(TalkImgDOFactory::newTalkImgDO).collect(Collectors.toList());
    }
}
