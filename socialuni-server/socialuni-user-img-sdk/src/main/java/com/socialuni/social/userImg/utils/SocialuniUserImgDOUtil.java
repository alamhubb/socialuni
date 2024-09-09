package com.socialuni.social.userImg.utils;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.dao.repository.SocialuniUserImgRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialuniUserImgDOUtil {
    private static SocialuniUserImgRepository userImgRepository;

    @Resource
    public void setUserImgRepository(SocialuniUserImgRepository userImgRepository) {
        SocialuniUserImgDOUtil.userImgRepository = userImgRepository;
    }

    public static SocialuniUserImgDo getUserImgNotNull(Long unionId) {
        if (unionId == null) {
            throw new SocialParamsException("内容标识不能为空");
        }
        SocialuniUserImgDo socialUserImgDO = userImgRepository.findOneByUnionId(unionId);
        if (socialUserImgDO == null) {
            throw new SocialParamsException("不存在的用户图片");
        }
        return socialUserImgDO;
    }

    public static List<SocialuniUserImgDo> getUserImgs(List<Long> unionIds) {
        List<SocialuniUserImgDo> list = new ArrayList<>();
        for (Long unionId : unionIds) {
            list.add(SocialuniUserImgDOUtil.getUserImgNotNull(unionId));
        }
        return list;
    }

    public static List<SocialuniUserImgDo> getUserImgsTop6(Long userId) {
        List<Long> integers = userImgRepository.findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }


    public static List<SocialuniUserImgDo> getUserImgsTop50(Long userId) {
        List<Long> integers = userImgRepository.findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }
}
