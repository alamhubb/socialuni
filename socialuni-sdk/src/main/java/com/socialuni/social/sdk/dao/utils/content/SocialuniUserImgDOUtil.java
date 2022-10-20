package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.user.sdk.api.SocialuniUserImgInterface;
import com.socialuni.social.user.sdk.model.SocialuniUserImgModel;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocialuniUserImgDOUtil {
    private static SocialuniUserImgInterface userImgRepository;

    @Resource
    public void setUserImgRepository(SocialuniUserImgInterface userImgRepository) {
        SocialuniUserImgDOUtil.userImgRepository = userImgRepository;
    }

    public static SocialuniUserImgModel getUserImgNotNull(Integer unionId) {
        if (unionId == null) {
            throw new SocialParamsException("内容标识不能为空");
        }
        SocialuniUserImgModel socialUserImgDO = userImgRepository.findOneByUnionId(unionId);
        if (socialUserImgDO == null) {
            throw new SocialParamsException("不存在的用户图片");
        }
        return socialUserImgDO;
    }

    public static List<SocialuniUserImgModel> getUserImgs(List<Integer> unionIds) {
        List<SocialuniUserImgModel> list = new ArrayList<>();
        for (Integer unionId : unionIds) {
            list.add(SocialuniUserImgDOUtil.getUserImgNotNull(unionId));
        }
        return list;
    }

    public static List<SocialuniUserImgModel> getUserImgsTop6(Integer userId) {
        List<Integer> integers = userImgRepository.findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }


    public static List<SocialuniUserImgModel> getUserImgsTop50(Integer userId) {
        List<Integer> integers = userImgRepository.findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.selfCanSeeContentStatus);
        return SocialuniUserImgDOUtil.getUserImgs(integers);
    }
}
