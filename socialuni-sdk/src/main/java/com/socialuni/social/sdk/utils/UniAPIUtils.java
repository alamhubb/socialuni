package com.socialuni.social.sdk.utils;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.model.QO.ContentAddQO;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.sdk.model.RO.user.SocialuniContentIdRO;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


@Component
@Slf4j
public class UniAPIUtils {
    static SocialuniUserAPI socialuniUserAPI;
    static SocialuniUnionIdInterface uniContentUnionIdRepository;

    static DevAccountInterface devAccountApi;

    @Autowired
    public void setDevAccountRepository(DevAccountInterface devAccountApi) {
        UniAPIUtils.devAccountApi = devAccountApi;
    }

    @Resource
    public void setUniContentUnionIdRepository(SocialuniUnionIdInterface uniContentUnionIdRepository) {
        UniAPIUtils.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UniAPIUtils.socialuniUserAPI = socialuniUserAPI;
    }

    public static <QO extends ContentAddQO, RO extends Object> ResultRO<RO> callUniAPIAndUpdateUid(SocialuniContentIdRO uniContentIdRO, Function<QO, ResultRO<RO>> callApi, QO contentAddQO) {
        //校验此条数据是否已经写入过。
        //存在appSocialuniId不为空，但是dataContentUnionId为空的情况，无后台模式。
        //首先判断是否为其他应用往本应用推送，否则就是自己的应用写入
        //1.执行业务
        //2.生成unionId
        //3.记录unionId
        //4.返回

        //判断这条动态是不是本应用的
        //mark 多库同步版本
        //都需要往中心推送，并且使用中心返回的unionId更新
        String apiUrl = SocialuniSystemConst.getSocialuniServerUrl();
        URI determinedBasePathUri = URI.create(Objects.requireNonNull(apiUrl));
        Map<String, Object> headerMap = new HashMap<String, Object>() {{
            put(SocialFeignHeaderName.socialuniSecretKey, SocialuniSystemConst.getDevSecretKey());
        }};
        //执行本系统逻辑
        //根据本系统uid获取unionId
        Integer unionId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uniContentIdRO.getId());

        //mark 多库同步版本
        ResultRO<RO> resultRO = callApi.apply(contentAddQO);
        uniContentIdRO = (SocialuniContentIdRO) resultRO.getData();

        //根据unionId更新为中心返回的uid
        SocialuniUnionIdFacede.updateUuidByUnionIdNotNull(unionId, uniContentIdRO.getId());

        return resultRO;


        /*else if (SocialAppConfig.serverIsCenter()) {
            List<DevAccountDO> devAccountDOS = devAccountRepository.findAll();
            for (DevAccountDO devAccountDO : devAccountDOS) {
                Integer pushServerId = DevAccountUtils.getDevIdNotNull();
                //如果为自己，或者为推送者
                if (devAccountDO.getId() == 1 || devAccountDO.getId().equals(pushServerId) || StringUtils.isEmpty(devAccountDO.getApiSecretKey()) || StringUtils.isEmpty(devAccountDO.getApiUrl())) {
                    continue;
                }
                //都需要往中心推送，并且使用中心返回的unionId更新
                URI determinedBasePathUri = URI.create(Objects.requireNonNull(devAccountDO.getApiUrl()));
                Map<String, Object> headerMap = new HashMap<String, Object>() {{
                    put(SocialFeignHeaderName.socialuniSecretKey, devAccountDO.getApiSecretKey());
                }};
                contentAddQO.setId(socialuniContentIdRO.getId());
                ResultRO<RO> resultRO = callApi.apply(determinedBasePathUri, headerMap, contentAddQO);
            }
        }*/
    }

    //list转换，TO类List转为RO类List
    /*public static <QO extends ContentAddQO, RO extends UniContentIdRO> UniContentIdRO callUniAPI(String contentType, Function<QO, RO> domain, Function<QO, ResultRO<RO>> callApi, QO contentAddQO) {
        String dataSocialuniId = RequestUtil.getDataOriginalSocialuniId();
        //校验此条数据是否已经写入过。
        Integer dataContentUnionId = contentAddQO.getId();
        //存在appSocialuniId不为空，但是dataContentUnionId为空的情况，无后台模式。
        DevAccountDO dataDevAccount = null;
        //首先判断是否为其他应用往本应用推送，否则就是自己的应用写入
        if (StringUtils.isEmpty(dataSocialuniId) && dataContentUnionId != null) {
            //有一个为空
            throw new SocialParamsException("缺少数据所有者id");
        }
        if (StringUtils.isNotEmpty(dataSocialuniId)) {
            //appSocialuniId不为空
            dataDevAccount = DevAccountUtils.getDevAccountBySocialuniId(dataSocialuniId);
            if (dataDevAccount == null) {
                throw new SocialParamsException("不存在的联盟Id");
            }
            //如果id为自己，则代表异常了，不应该出现的情况，不该自己给自己推
            Integer dataDevId = dataDevAccount.getId();
            if (dataDevId == 1) {
                throw new SocialBusinessException("数据重复写入错误");
            }
            //异常，有推送时，则开发者肯定不为中心，而是三方
            if (DevAccountUtils.pushServer()) {
                throw new SocialSystemException("逻辑错误，中心重复调用自己，或者秘钥泄露");
            }
            //仅仅是个校验，防止重复写入 ， dataSocialuniId都不空才能查，dataSociuni为空，则肯定thirdId为空
            if (dataContentUnionId != null) {
                //如果已经存在此动态，则无需重复添加，直接返回
                UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByOriginalDevIdAndOriginalContentUnionId(dataDevId, dataContentUnionId);
                if (uniContentUnionIdDO != null) {
                    return null;
                }
            }
        }
        //推送的需要校验，非推送的不用校验
        //校验功能搞好了，接下来就可以直接写入数据了
        //不为空
        UniContentIdRO socialuniContentIdRO = domain.apply(contentAddQO);


        //这就写入了数据，然后写入unionId表
        //如果自身为中心

        contentAddQO.setContentType(contentType);

        //无后台模式
        if (dataContentUnionId == null) {
            //如果无后台模式会为空
            dataContentUnionId = UnionIdDbUtil.createUnionIdByWrite(socialuniContentIdRO, dataContentUnionId);
            //错误，无后台，非pushserver，但是也可push
//            if (DevAccountUtils.pushServer()) {
            if (SocialAppConfig.hasCenterServer()) {
                contentAddQO.setId(dataContentUnionId);
            }
        } else {
            //中心了
            UnionIdDbUtil.createUnionIdByWrite(socialuniContentIdRO, dataContentUnionId);
        }
        //如果配置了中心
        if (SocialAppConfig.hasCenterServer()) {
            //数据id为空
            callApi.apply(contentAddQO);
        }
        //如果是中心 m则向所有子节点发请求， owner是传过来的， id自己的，秘钥自己的，从数据库里获取，自己对应对方的秘钥

        //如果是子应用 则向中心发请求， 秘钥配置在启动设置中， owner是自己的id，id也设置


        //如果中心，则是子应用推过来的， 只要看有没有 ownerid就行，怎么都是发所有。

        //如果是子应用，则是中心推过来的，肯定会有contentId


        //首先判断有没有秘钥，有秘钥则代表推送


        //给中心设置开发者秘钥，在应用启动时，判断是否设置了中心服务器，

//        devAccountRepository.findAllByStatusAndApiUrlNotNullAndApiSecretKeyNotNull(CommonStatus.enable);
        return socialuniContentIdRO;
    }*/
}
