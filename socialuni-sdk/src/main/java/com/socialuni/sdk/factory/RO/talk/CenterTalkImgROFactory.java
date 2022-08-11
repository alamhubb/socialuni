package com.socialuni.sdk.factory.RO.talk;

import com.socialuni.sdk.model.RO.talk.CenterTalkImgRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkImgRO;
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
public class CenterTalkImgROFactory {
    //需要user因为，user需要外部传入，区分center和social
    //用户详情
    public static CenterTalkImgRO getHomeTalkImgRO(SocialTalkImgRO imgRO) {
        CenterTalkImgRO centerTalkImgRO = new CenterTalkImgRO();
        String id = UnionIdDbUtil.getUidByUnionIdNotNull(imgRO.getId());
        centerTalkImgRO.setId(id);
        centerTalkImgRO.setSrc(imgRO.getSrc());
        centerTalkImgRO.setAspectRatio(imgRO.getAspectRatio());
        return centerTalkImgRO;
    }

    public static List<CenterTalkImgRO> getHomeTalkImgROS(List<SocialTalkImgRO> imgROS) {
        return ListConvertUtil.toList(CenterTalkImgROFactory::getHomeTalkImgRO, imgROS);
    }

    public static List<CenterTalkImgRO> getCenterTalkImgROS(List<CenterTalkImgRO> imgROS) {
        return ListConvertUtil.toList(CenterTalkImgRO::new, imgROS);
    }
}