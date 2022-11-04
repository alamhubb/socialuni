package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkImgDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.sdk.dao.mapper.TalkImgMapper;
import com.socialuni.social.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static SocialuniTalkImgDO saveTalkImgDO(SocialuniTalkImgDO talkImgDO){
        return talkImgRepository.save(talkImgDO);
    }

    public static List<SocialuniTalkImgDO> getTalkImgsTop3(Integer talkId) {
        List<SocialuniTalkImgDO> talkImgDOS = talkImgRepository.findUnionIdTop3ByTalkIdOrderByIdAsc(talkId);
        for (SocialuniTalkImgDO talkImgDO : talkImgDOS) {
            if (ObjectUtils.isEmpty(talkImgDO.getUnionId())) {
                Integer unionId = SocialuniUnionIdFacede.createTalkImgUnionId();
                talkImgDO.setUnionId(unionId);
                SocialuniTalkImgDOUtil.saveTalkImgDO(talkImgDO);
            }
        }
        List<Integer> integers = talkImgDOS.stream().map(SocialuniUnionContentBaseDO::getUnionId).collect(Collectors.toList());

        return SocialuniTalkImgDOUtil.getTalkImgs(integers);
    }
}