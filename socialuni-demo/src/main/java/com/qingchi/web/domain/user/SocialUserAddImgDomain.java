package com.qingchi.web.domain.user;

import com.socialuni.social.sdk.repository.UserRepository;
import com.socialuni.social.sdk.service.comment.IllegalWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialUserAddImgDomain {
    @Resource
    IllegalWordService illegalWordService;
    @Resource
    UserRepository userRepository;

        /*
    public SocialMineUserDetailRO addImg(SocialUserImgAddQO img, UserDO mineUser) {
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }
        List<UserImgDO> userImgDOS = UserImgDOUtils.getImgs(mineUser.getId());
        if (userImgDOS.size() > 2) {
            throw new SocialBusinessException("最多上传3张照片，请删除后继续！");
        }

        UserImgDO userImgDO = UserImgDOFactory.toUserImgDO(img, mineUser);
        //如果用户已认证，则上传的照片必须为已认证的
        if (mineUser.getIsSelfAuth()) {
            Optional<IdentityImgDO> optionalIdentityImgDO = identityImgRepository.findFirstByUserIdAndStatusOrderByIdDesc(user.getId(), UserStatus.enable);
            if (optionalIdentityImgDO.isPresent()) {
                IdentityImgDO identityImgDO = optionalIdentityImgDO.get();
                boolean isAuth = TencentCloud.imgAuth(userImgDO.getSrc(), identityImgDO.getSrc());
                //不为本人，则提示，为本人则继续下面的逻辑
                if (!isAuth) {
                    throw new SocialBusinessException("用户认证后，只能上传与认证信息相符的本人清晰露脸的真实照片");
                }
            } else {
                log.logger.error("系统异常，不应该出现已认证却没有记录的情况");
                throw new SocialBusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }

        mineUser = userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(mineUser);
        return new ResultRO<>(centerMineUserDetailRO);
    }*/
}
