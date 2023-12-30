package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
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

    public static SocialuniUserImgDo getUserImgNotNull(Integer unionId) {
        if (unionId == null) {
            throw new SocialParamsException("内容标识不能为空");
        }
        SocialuniUserImgDo socialUserImgDO = userImgRepository.findOneByUnionId(unionId);
        if (socialUserImgDO == null) {
            throw new SocialParamsException("不存在的用户图片");
        }
        return socialUserImgDO;
    }

    public static List<SocialuniUserImgDo> getUserImgs(List<Integer> unionIds) {
        List<SocialuniUserImgDo> list = new ArrayList<>();
        for (Integer unionId : unionIds) {
            list.add(SocialuniUserImgDOUtil.getUserImgNotNull(unionId));
        }
        return list;
    }

    public static List<SocialuniUserImgDo> getUserImgsTop6(Integer userId) {
        List<Integer> integers = userImgRepository.findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }


    public static List<SocialuniUserImgDo> getUserImgsTop50(Integer userId) {
        List<Integer> integers = userImgRepository.findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }
}
