package logic;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import factory.SocialuniPeiwanInfoDOFactory;
import factory.SocialuniPeiwanInfoROFactory;
import model.DO.SocialuniPeiwanInfoDO;
import model.QO.SocialuniPeiwanInfoRO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniAdminPeiwanService {
    @Resource
    SocialUserManage socialUserManage;


    public ResultRO<Void> addPeiwanInfo(@RequestBody SocialuniPeiwanInfoRO socialuniPeiwanInfoAddQO) {
        SocialuniUserDo mineUser = socialUserManage.createUserByPhoneLogin();

        SocialuniPeiwanInfoDO socialuniPeiwanInfoDO = SocialuniPeiwanInfoDOFactory.createPeiwanDO(mineUser, socialuniPeiwanInfoAddQO);

        SocialuniRepositoryFacade.save(socialuniPeiwanInfoDO);

        return ResultRO.success();
    }


    public ResultRO<List<SocialuniPeiwanInfoRO>> getPeiwanInfoList() {

        List<SocialuniPeiwanInfoDO> socialuniPeiwanInfoDOS = SocialuniRepositoryFacade.findByAllByIdDesc(SocialuniPeiwanInfoDO.class);

        List<SocialuniPeiwanInfoRO> socialuniPeiwanInfoROS = SocialuniPeiwanInfoROFactory.getPeiwanRoList(socialuniPeiwanInfoDOS);
        return ResultRO.success(socialuniPeiwanInfoROS);
    }

}
