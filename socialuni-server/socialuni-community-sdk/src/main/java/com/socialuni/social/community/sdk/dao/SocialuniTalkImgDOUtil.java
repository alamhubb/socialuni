package com.socialuni.social.community.sdk.dao;

import com.socialuni.social.common.api.utils.SnowflakeIdUtil;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkImgDO;
import com.socialuni.social.common.api.entity.SocialuniUnionContentBaseDO;
import com.socialuni.social.community.sdk.dao.mapper.TalkImgMapper;
import com.socialuni.social.community.sdk.repository.TalkImgRepository;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
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

    public static SocialuniTalkImgDO getTalkImgNotNull(Long unionId) {
        if (unionId == null) {
            throw new SocialParamsException("内容标识不能为空");
        }
        SocialuniTalkImgDO socialTalkImgDO = talkImgRepository.findOneByUnionId(unionId);
        if (socialTalkImgDO == null) {
            throw new SocialParamsException("不存在的动态图片");
        }
        return socialTalkImgDO;
    }

    public static List<SocialuniTalkImgDO> getTalkImgs(List<Long> unionIds) {
        List<SocialuniTalkImgDO> list = new ArrayList<>();
        for (Long unionId : unionIds) {
            list.add(SocialuniTalkImgDOUtil.getTalkImgNotNull(unionId));
        }
        return list;
    }

    public static SocialuniTalkImgDO saveTalkImgDO(SocialuniTalkImgDO talkImgDO){
        return talkImgRepository.save(talkImgDO);
    }

    public static List<SocialuniTalkImgDO> getTalkImgsTop3(Long talkId) {
        List<SocialuniTalkImgDO> talkImgDOS = talkImgRepository.findUnionIdTop3ByTalkIdOrderByIdAsc(talkId);
        for (SocialuniTalkImgDO talkImgDO : talkImgDOS) {
            if (ObjectUtils.isEmpty(talkImgDO.getUnionId())) {
                Long unionId = SocialuniUnionIdFacede.createTalkImgUnionId(SnowflakeIdUtil.nextIdStr());
                talkImgDO.setUnionId(unionId);
                SocialuniTalkImgDOUtil.saveTalkImgDO(talkImgDO);
            }
        }
        List<Long> integers = talkImgDOS.stream().map(SocialuniUnionContentBaseDO::getUnionId).collect(Collectors.toList());

        return SocialuniTalkImgDOUtil.getTalkImgs(integers);
    }
}