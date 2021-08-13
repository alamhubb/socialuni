package com.socialuni.social.sdk.utils.model.DO;

import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.constant.status.ContentStatus;
import com.socialuni.social.sdk.repository.UserImgRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserImgDOUtils {
    private static UserImgRepository userImgRepository;

    @Resource
    public void setUserImgRepository(UserImgRepository userImgRepository) {
        UserImgDOUtils.userImgRepository = userImgRepository;
    }

    public static List<UserImgDO> getImgs(Integer userId) {
        return userImgRepository.findTop3ByUserIdAndStatusInOrderByCreateTimeDesc(userId, ContentStatus.otherCanSeeContentStatus);
    }

    public static UserImgDO find(Integer imgId) {
        return userImgRepository.findById(imgId).get();
    }
}
