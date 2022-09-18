package com.socialuni.sdk.dao.utils.content;

import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.sdk.dao.mapper.TalkImgMapper;
import com.socialuni.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SocialuniTalkImgDOUtil {
    private static TalkImgRepository talkImgRepository;
    private static TalkImgMapper talkImgMapper;

    @Resource
    public void setTalkImgMapper(TalkImgMapper talkImgMapper) {
        SocialuniTalkImgDOUtil.talkImgMapper = talkImgMapper;
    }

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        SocialuniTalkImgDOUtil.talkImgRepository = talkImgRepository;
    }

    public static SocialuniTalkImgDO getTalkImgNotNull(Integer unionId) {
        if (unionId == null) {
            throw new SocialParamsException("内容标识不能为空");
        }
        SocialuniTalkImgDO socialTalkImgDO = talkImgRepository.findOneByUnionId(unionId);
        if (socialTalkImgDO == null) {
            throw new SocialParamsException("不存在的动态图片");
        }
        return socialTalkImgDO;
    }

    public static List<SocialuniTalkImgDO> getTalkImgs(List<Integer> unionIds) {
        List<SocialuniTalkImgDO> list = new ArrayList<>();
        for (Integer unionId : unionIds) {
            list.add(SocialuniTalkImgDOUtil.getTalkImgNotNull(unionId));
        }
        return list;
    }

    public static List<SocialuniTalkImgDO> getTalkImgsTop3(Integer talkId) {
        List<Integer> integers = talkImgRepository.findUnionIdTop3ByTalkIdOrderByIdAsc(talkId);
        return SocialuniTalkImgDOUtil.getTalkImgs(integers);
    }
}