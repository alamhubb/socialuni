package com.socialuni.center.web.utils.model.DO;

import com.socialuni.center.web.model.DO.user.UserImgDO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.center.web.repository.UserImgRepository;
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
