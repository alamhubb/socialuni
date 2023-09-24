package com.socialuni.social.peiwan.sdk.factory;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.sdk.model.RO.SocialuniRectangleRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.common.sdk.utils.PositionUtil;
import com.socialuni.social.peiwan.sdk.dao.rep.SocialuniPeiwanImgRepository;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoDO;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SocialuniPeiwanInfoROFactory {

    private static SocialuniPeiwanImgRepository socialuniPeiwanImgRepository;

    @Resource
    public void setSocialuniPeiwanImgRepository(SocialuniPeiwanImgRepository socialuniPeiwanImgRepository) {
        SocialuniPeiwanInfoROFactory.socialuniPeiwanImgRepository = socialuniPeiwanImgRepository;
    }

    public static SocialuniPeiwanInfoRO getPeiwanRO(SocialuniPeiwanInfoDO peiwanInfoDO) {
        SocialuniPeiwanInfoRO peiwanInfoRO = new SocialuniPeiwanInfoRO();

        String userId = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(peiwanInfoDO.getUserId());

        peiwanInfoRO.setUserId(userId);
        peiwanInfoRO.setNickname(peiwanInfoDO.getNickname());
        peiwanInfoRO.setLng(peiwanInfoDO.getLng());
        peiwanInfoRO.setLat(peiwanInfoDO.getLat());
        peiwanInfoRO.setCity(peiwanInfoDO.getCity());
        peiwanInfoRO.setDistrict(peiwanInfoDO.getDistrict());
        peiwanInfoRO.setAvatar(peiwanInfoDO.getAvatar());

        SocialuniRectangleRO rectangleVO = PositionUtil.getRectangle();


        Double dbLon = peiwanInfoDO.getLng();
        Double dbLat = peiwanInfoDO.getLat();

        SocialuniRectangleRO queryRO = new SocialuniRectangleRO(rectangleVO.getLon(), rectangleVO.getLat());
        SocialuniRectangleRO dataRO = new SocialuniRectangleRO(dbLon, dbLat);

        Double distance = PositionUtil.getDistance(queryRO, dataRO);
        peiwanInfoRO.setDistance(distance);

        String skills = peiwanInfoDO.getSkills();
        if (StringUtils.isEmpty(skills)) {
            peiwanInfoRO.setSkills(new ArrayList<>());
        } else {
            peiwanInfoRO.setSkills(Arrays.asList(skills.split(",")));
        }

        List<SocialuniPeiwanInfoImgDO> list = socialuniPeiwanImgRepository.findAllByUserIdAndStatusOrderByLevelAscIdAsc(peiwanInfoDO.getUserId(), SocialuniCommonStatus.enable);

        List<SocialuniPeiwanInfoImgRO> imgROES = SocialuniPeiwanInfoImgROFactory.getPeiwanImgRoList(list);

        peiwanInfoRO.setImgs(imgROES);

        return peiwanInfoRO;
    }

    public static List<SocialuniPeiwanInfoRO> getPeiwanRoList(List<SocialuniPeiwanInfoDO> peiwanInfoDOS) {
        return ListConvertUtil.toList(SocialuniPeiwanInfoROFactory::getPeiwanRO, peiwanInfoDOS);
//        return peiwanInfoDOS.stream().map(SocialuniPeiwanInfoROFactory::getPeiwanRO).collect(Collectors.toList());
    }
}
