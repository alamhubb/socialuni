package com.socialuni.center.web.utils;

import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.repository.dev.DevAccountRepository;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.model.model.QO.ContentAddQO;
import com.socialuni.social.model.model.RO.community.SocialuniContentIdRO;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;


@Component
public class UniAPIUtils {
    static SocialuniUserAPI socialuniUserAPI;
    static UniContentUnionIdRepository uniContentUnionIdRepository;

    static DevAccountRepository devAccountRepository;

    @Resource
    public void setDevAccountRepository(DevAccountRepository devAccountRepository) {
        UniAPIUtils.devAccountRepository = devAccountRepository;
    }

    @Resource
    public void setUniContentUnionIdRepository(UniContentUnionIdRepository uniContentUnionIdRepository) {
        UniAPIUtils.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    @Resource
    public void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UniAPIUtils.socialuniUserAPI = socialuniUserAPI;
    }

    //list转换，TO类List转为RO类List
    public static <QO extends ContentAddQO, RO extends SocialuniContentIdRO> SocialuniContentIdRO callUniAPI(String contentType, Function<QO, RO> domain, Function<QO, ResultRO<RO>> callApi, QO contentAddQO) {
        String dataSocialuniId = RequestUtil.getDataSocialuniId();
        //校验此条数据是否已经写入过。
        String dataContentUnionIdStr = contentAddQO.getId();
        //存在appSocialuniId不为空，但是dataContentUnionId为空的情况，无后台模式。
        DevAccountDO dataDevAccount = null;
        //首先判断是否为其他应用往本应用推送，否则就是自己的应用写入
        if (StringUtils.isEmpty(dataSocialuniId) && !StringUtils.isEmpty(dataContentUnionIdStr)) {
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
            if (StringUtils.isNotEmpty(dataContentUnionIdStr)) {
                //如果已经存在此动态，则无需重复添加，直接返回
                UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByDataDevIdAndDataContentUnionId(dataDevId, Integer.valueOf(dataContentUnionIdStr));
                if (uniContentUnionIdDO != null) {
                    return null;
                }
            }
        }
        //推送的需要校验，非推送的不用校验
        //校验功能搞好了，接下来就可以直接写入数据了
        //不为空
        SocialuniContentIdRO socialuniContentIdRO = domain.apply(contentAddQO);

        //这就写入了数据，然后写入unionId表
        //如果自身为中心
        UniContentUnionIdDO uniContentUnionIdDO;
        if (DevAccountUtils.pushServer()) {
            //如果无后台模式会为空
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, DevAccountUtils.getDataDevIdNotNull(), null, DevAccountUtils.getDevIdNotNull(), socialuniContentIdRO.getId());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        } else {
            //无后台模式
            if (StringUtils.isEmpty(dataContentUnionIdStr)) {
                //如果无后台模式会为空
                uniContentUnionIdDO = new UniContentUnionIdDO(contentType, DevAccountUtils.getDataDevIdNotNull(), null, DevAccountUtils.getDevIdNotNull(), socialuniContentIdRO.getId());
                uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            } else {
                //中心了
                uniContentUnionIdDO = new UniContentUnionIdDO(contentType, DevAccountUtils.getDataDevIdNotNull(), Integer.valueOf(dataContentUnionIdStr), DevAccountUtils.getDevIdNotNull(), socialuniContentIdRO.getId());
                uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            }
        }


        //如果是中心 m则向所有子节点发请求， owner是传过来的， id自己的，秘钥自己的，从数据库里获取，自己对应对方的秘钥

        //如果是子应用 则向中心发请求， 秘钥配置在启动设置中， owner是自己的id，id也设置


        //如果中心，则是子应用推过来的， 只要看有没有 ownerid就行，怎么都是发所有。

        //如果是子应用，则是中心推过来的，肯定会有contentId


        //首先判断有没有秘钥，有秘钥则代表推送


        //给中心设置开发者秘钥，在应用启动时，判断是否设置了中心服务器，

//        devAccountRepository.findAllByStatusAndApiUrlNotNullAndApiSecretKeyNotNull(CommonStatus.enable);


        //如果配置了中心
        if (SocialAppConfig.hasCenterServer()) {

            //数据id为空
            if (StringUtils.isEmpty(dataContentUnionIdStr) && DevAccountUtils.pushServer()) {
                contentAddQO.setId(uniContentUnionIdDO.getId().toString());
            }
            callApi.apply(contentAddQO);
        } else {

        }
        return socialuniContentIdRO;
    }
}
