package com.socialuni.sdk.controller.upgrade0823;

import com.socialuni.sdk.factory.DO.UnionIdDOFactory;
import com.socialuni.sdk.model.DO.UnionIdDO;
import com.socialuni.sdk.repository.dev.ThirdUserAuthRepository;
import com.socialuni.sdk.repository.dev.ThirdUserRepository;
import com.socialuni.sdk.repository.dev.ThirdUserTokenRepository;
import com.socialuni.sdk.repository.UnionIdRepository;
import com.socialuni.social.constant.ContentType;
import com.socialuni.sdk.domain.phone.SocialBindUserSocialuniAccountDomain;
import com.socialuni.sdk.repository.UserRepository;
import com.socialuni.social.web.sdk.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestUnionIdService {
    @Resource
    UserRepository userRepository;
    @Resource
    UnionIdRepository unionIdRepository;
    @Resource
    ThirdUserRepository thirdUserRepository;
    @Resource
    ThirdUserAuthRepository thirdUserAuthRepository;
    @Resource
    ThirdUserTokenRepository thirdUserTokenRepository;
    @Resource
    SocialBindUserSocialuniAccountDomain socialBindUserSocialuniAccountDomain;

    public void unionid() {
        final Integer devId = 1;

        Date date = new Date();

        List<UnionIdDO> unionIdDOS = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
//            String uid = UUIDUtil.getUUID();
            String uid = Integer.toString(i);
            Date emptyDate = new Date(0);
            UnionIdDO unionIdDO = UnionIdDOFactory.createUnionDO(ContentType.user, i, devId, date, emptyDate, uid, i);

            unionIdRepository.save(unionIdDO);
            Date endDate = new Date();
            /*unionIdDOS.add(unionIdDO);
            if (i % 100 == 0) {
                log.info("100条耗时：{}秒", (endDate.getTime() - date.getTime()) / 100);
                unionIdRepository.saveAll(unionIdDOS);
                endDate = new Date();
                unionIdDOS = new ArrayList<>();
                log.info("完成：" + i);
                log.info("100条耗时：{}秒", (endDate.getTime() - date.getTime()) / 100);
            }*/
            log.info("1条耗时：{}秒", (endDate.getTime() - date.getTime()) / 1000);
        }

        Date endDate = new Date();

        log.info("1000条耗时：{}秒", (endDate.getTime() - date.getTime()) / 1000);
    }

    public void unionid1() {
        final Integer devId = 1;

        Date date = new Date();

        List<UnionIdDO> unionIdDOS = new ArrayList<>();
        for (int i = 10; i < 1000; i++) {
            String uid = UUIDUtil.getUUID();
            UnionIdDO unionIdDO = UnionIdDOFactory.createUnionDO(ContentType.user, i, devId, date, date, uid, i);
            unionIdDOS.add(unionIdDO);
            if (i % 100 == 0) {
                Date endDate = new Date();
                log.info("100条耗时：{}秒", (endDate.getTime() - date.getTime()) / 100);
                unionIdRepository.saveAll(unionIdDOS);
                endDate = new Date();
                unionIdDOS = new ArrayList<>();
                log.info("完成：" + i);
                log.info("100条耗时：{}秒", (endDate.getTime() - date.getTime()) / 100);
            }
        }

        Date endDate = new Date();

        log.info("1000条耗时：{}秒", (endDate.getTime() - date.getTime()) / 100);
    }

    /*@Async
    public void unionid() {
        final Integer devId = 1;
        List<Integer> userIds = userRepository.findAllUserIds();
        List<UnionIdDO> unionIdDOS = new ArrayList<>();
        List<ThirdUserDO> thirdUserDOS = new ArrayList<>();
        List<ThirdUserAuthDO> thirdUserAuthDOS = new ArrayList<>();
        List<ThirdUserTokenDO> thirdUserTokenDOS = new ArrayList<>();
        Date date = new Date();
        for (Integer userId : userIds) {
            UnionIdDO unionIdDO = UnionIdDOFactory.createUnionDO(ContentType.user, userId, devId, date, date, userId);
            unionIdDOS.add(unionIdDO);

            ThirdUserDO threeUserDO = new ThirdUserDO(devId, userId, unionIdDO.getUnionId());
            thirdUserDOS.add(threeUserDO);

            ThirdUserAuthDO threeUserAuthUserDO = new ThirdUserAuthDO(threeUserDO, AuthType.user);
            ThirdUserAuthDO threeUserAuthDO = new ThirdUserAuthDO(threeUserDO, AuthType.phone);
            thirdUserAuthDOS.add(threeUserAuthUserDO);
            thirdUserAuthDOS.add(threeUserAuthDO);

            String appToken = SocialTokenUtil.generateTokenByUserKey(unionIdDO.getUnionId());
            ThirdUserTokenDO thirdToken = new ThirdUserTokenDO(userId, appToken, devId, unionIdDO.getUnionId());
            thirdUserTokenDOS.add(thirdToken);

            socialBindUserSocialuniAccountDomain.bindUserSocialAccount(userId, appToken, unionIdDO.getUnionId());
        }
        unionIdRepository.saveAll(unionIdDOS);
        thirdUserRepository.saveAll(thirdUserDOS);
        thirdUserAuthRepository.saveAll(thirdUserAuthDOS);
        thirdUserTokenRepository.saveAll(thirdUserTokenDOS);
    }*/
}
