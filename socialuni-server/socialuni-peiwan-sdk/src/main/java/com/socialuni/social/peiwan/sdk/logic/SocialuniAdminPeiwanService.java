package com.socialuni.social.peiwan.sdk.logic;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserRepositoryFacede;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.peiwan.sdk.factory.SocialuniPeiwanInfoDOFactory;
import com.socialuni.social.peiwan.sdk.factory.SocialuniPeiwanInfoImgDOFactory;
import com.socialuni.social.peiwan.sdk.factory.SocialuniPeiwanInfoImgROFactory;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.peiwan.sdk.factory.SocialuniPeiwanInfoROFactory;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SocialuniAdminPeiwanService {
    @Resource
    SocialUserManage socialUserManage;


    public ResultRO<Void> addPeiwanInfo(@RequestBody SocialuniPeiwanInfoRO socialuniPeiwanInfoAddQO) {
        SocialuniUserDo mineUser = socialUserManage.createUser();

        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = SocialuniPeiwanInfoDOFactory.createPeiwanDO(mineUser, socialuniPeiwanInfoAddQO);

        SocialuniRepositoryFacade.save(socialuniPeiwanInfoDO);

        return ResultRO.success();
    }


    public ResultRO<Void> addPeiwanInfoList(@RequestBody List<SocialuniPeiwanInfoRO> socialuniPeiwanInfoAddQOs) {

        List<SocialuniPeiwanInfoDO> list = new ArrayList<>();

        for (SocialuniPeiwanInfoRO socialuniPeiwanInfoAddQO : socialuniPeiwanInfoAddQOs) {
            SocialuniUserDo mineUser = socialUserManage.createUser();

            SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = SocialuniPeiwanInfoDOFactory.createPeiwanDO(mineUser, socialuniPeiwanInfoAddQO);
            list.add(socialuniPeiwanInfoDO);
        }


        SocialuniRepositoryFacade.saveAll(list);

        return ResultRO.success();
    }


    public ResultRO<String> updatePeiwanAvatar(String userId, String src) {

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserByUuid(userId);

        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = SocialuniUserRepositoryFacede.findByUserIdNotNull(socialuniUserDo.getUserId(), SocialuniPeiwanInfoDO.class);

        socialuniPeiwanInfoDO.setAvatar(src);

        return ResultRO.success(SocialuniSystemConst.getStaticResourceUrl() + src);
    }


    public ResultRO<List<SocialuniPeiwanInfoRO>> queryPeiwanInfoList() {

        List<SocialuniPeiwanInfoDO> socialuniPeiwanInfoDOS = SocialuniRepositoryFacade.findByAllByOrderByUpdateTimeDesc(SocialuniPeiwanInfoDO.class);

        List<SocialuniPeiwanInfoRO> socialuniPeiwanInfoROS = SocialuniPeiwanInfoROFactory.getPeiwanRoList(socialuniPeiwanInfoDOS);
        return ResultRO.success(socialuniPeiwanInfoROS);
    }


    public ResultRO<Void> updatePeiwanList(List<SocialuniPeiwanInfoRO> peiwanInfoROS) {

        List<SocialuniPeiwanInfoDO> socialuniPeiwanInfoDOS = new ArrayList<>();
        List<SocialuniPeiwanInfoImgDO> socialuniPeiwanInfoImgDOS = new ArrayList<>();


        for (SocialuniPeiwanInfoRO peiwanInfoRO : peiwanInfoROS) {
            Long userId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(peiwanInfoRO.getUserId());
            SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = SocialuniUserRepositoryFacede.findFirstByUserIdOrderByIdDesc(userId, SocialuniPeiwanInfoDO.class);

            socialuniPeiwanInfoDO = SocialuniPeiwanInfoDOFactory.createPeiwanDO(socialuniPeiwanInfoDO, peiwanInfoRO);
            socialuniPeiwanInfoDO.setUpdateTime(new Date());

            List<SocialuniPeiwanInfoImgRO> imgs = peiwanInfoRO.getImgs();
            for (SocialuniPeiwanInfoImgRO img : imgs) {
                Long imgId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(img.getId());

                Integer index = imgs.indexOf(img);

                SocialuniPeiwanInfoImgDO imgDO = SocialuniRepositoryFacade.findByUnionId(imgId, SocialuniPeiwanInfoImgDO.class);
                if (!imgDO.getLevel().equals(index)) {
                    imgDO.setLevel(index);
                    socialuniPeiwanInfoImgDOS.add(imgDO);
                }

            }

            socialuniPeiwanInfoDOS.add(socialuniPeiwanInfoDO);

        }

        SocialuniRepositoryFacade.saveAll(socialuniPeiwanInfoDOS);
        SocialuniRepositoryFacade.saveAll(socialuniPeiwanInfoImgDOS);

        return ResultRO.success();
    }

    @Async
    public ResultRO<Void> deletePeiwanImg(String imgId) {
        Long unionId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(imgId);

        SocialuniPeiwanInfoImgDO socialuniPeiwanInfoImgDO = SocialuniRepositoryFacade.findByUnionId(unionId, SocialuniPeiwanInfoImgDO.class);

        socialuniPeiwanInfoImgDO.setStatus(SocialuniCommonStatus.delete);

        SocialuniRepositoryFacade.save(socialuniPeiwanInfoImgDO);

        return ResultRO.success();
    }

    public ResultRO<List<SocialuniPeiwanInfoImgRO>> addPeiwanImgList(String userId, List<SocialuniImgAddQO> imgAddQOS) {

        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserByUuid(userId);

        List<SocialuniPeiwanInfoImgDO> socialuniPeiwanInfoImgDOS = SocialuniPeiwanInfoImgDOFactory.createPeiwanImgDOs(socialuniUserDo.getUserId(), imgAddQOS);

        socialuniPeiwanInfoImgDOS = SocialuniRepositoryFacade.saveAll(socialuniPeiwanInfoImgDOS);

        List<SocialuniPeiwanInfoImgRO> imgROES = SocialuniPeiwanInfoImgROFactory.getPeiwanImgRoList(socialuniPeiwanInfoImgDOS);

        return ResultRO.success(imgROES);
    }

}
