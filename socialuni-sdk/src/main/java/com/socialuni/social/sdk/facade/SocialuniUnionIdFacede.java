package com.socialuni.social.sdk.facade;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
@Component
@Slf4j
public class SocialuniUnionIdFacede {
    private static SocialuniUnionIdInterface socialuniUnionIdApi;

    @Resource
    public void setSocialuniUnionIdCache(SocialuniUnionIdInterface socialuniUnionIdApi) {
        SocialuniUnionIdFacede.socialuniUnionIdApi = socialuniUnionIdApi;
    }

    //根据空的创建， 本系统写入数据时，需要先创建，然后写入内容表unionId，然后再根据返回内容更新uid
    public static Integer createUserUnionId() {
        return createUnionIdByContentType(SocialuniContentType.user);
    }

    public static Integer createUserImgUnionId() {
        return createUnionIdByContentType(SocialuniContentType.userImg);
    }

    public static Integer createTalkImgUnionId() {
        return createUnionIdByContentType(SocialuniContentType.talkImg);
    }

    public static Integer createTalkUnionId() {
        return createUnionIdByContentType(SocialuniContentType.talk);
    }

    public static Integer createCommentUnionId() {
        return createUnionIdByContentType(SocialuniContentType.comment);
    }

    //自身创建
    private static Integer createUnionIdByContentType(String contentType) {
        SocialuniUnionIdModler uniContentUnionIdDO = new SocialuniUnionIdModler(contentType, UUIDUtil.getUUID(), DevAccountFacade.getDevIdNotNull());
        uniContentUnionIdDO = socialuniUnionIdApi.save(uniContentUnionIdDO);
        //有的话更新
        return uniContentUnionIdDO.getId();
    }

    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static void updateUuidByUnionIdNotNull(Integer unionId, String uuid) {
        SocialuniUnionIdModler uniContentUnionIdDO = getUnionDOByUnionIdNotNull(unionId);
        //没有写入
        uniContentUnionIdDO.setUuid(uuid);
        socialuniUnionIdApi.save(uniContentUnionIdDO);
    }

    //social层，根据unionId获取uid，不可为空
    public static String getUuidByUnionIdNotNull(Integer unionId) {
        return getUnionDOByUnionIdNotNull(unionId).getUuid();
    }

    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static SocialuniUnionIdModler getUnionDOByUnionIdNotNull(Integer unionId) {
        if (unionId == null) {
            throw new SocialParamsException("无效的内容标识4");
        }
        SocialuniUnionIdModler uniContentUnionIdDO = socialuniUnionIdApi.findById(unionId);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("无效的内容标识5");
        }
        return uniContentUnionIdDO;
    }

    public static String createUnionIdByUuid(String contentType, String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标识3");
        }
        SocialuniUnionIdModler uniContentUnionIdDO = getUnionByUuidAllowNull(uuid);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new SocialuniUnionIdModler(contentType, uuid, DevAccountFacade.getCenterDevIdNotNull());
            socialuniUnionIdApi.save(uniContentUnionIdDO);
            //有的话更新
        }
        return uuid;
    }


    //结果不可为空 ，为前台传入的数据,根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static Integer getUnionIdByUuidNotNull(String uuid) {
        return getUnionByUuidNotNull(uuid).getId();
    }

    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static SocialuniUnionIdModler getUnionByUuidNotNull(String uuid) {
        SocialuniUnionIdModler uniContentUnionIdDO = getUnionByUuidAllowNull(uuid);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识2");
        }
        return uniContentUnionIdDO;
    }

    //外部使用可能查询不存在的
    public static SocialuniUnionIdModler getUnionByUuidAllowNull(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标识1");
        }
        return socialuniUnionIdApi.findByUuId(uuid);
    }


    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, SocialuniContentType.talk);
    }

    public static List<Integer> getContentIdsByUserUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, SocialuniContentType.user);
    }

    public static List<Integer> getContentIdsByUserImgUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, SocialuniContentType.userImg);
    }

    public static List<Integer> getContentIdsByCommentUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, SocialuniContentType.comment);
    }

    public static List<Integer> getContentIdsByMessageUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, SocialuniContentType.message);
    }

    public static List<Integer> getContentIdsByUnionIds(List<String> contentUnionIds, String contentType) {
        List<Integer> ids = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(contentUnionIds)) {
            for (String uuid : contentUnionIds) {
//                log.info("查询单个id：" + System.currentTimeMillis());
                Integer id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uuid);
                ids.add(id);
            }
        }
        if (ids.size() == 0) {
            //必须使用这个，后续需要往里面add元素
            ids = new ArrayList<>();
            ids.add(0);
//            ids = null;
        }
        return ids;
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    //根据uid获取真实id
    /*public static Integer getResultByUnionId(String contentType, String unionId) {
//        Integer resultRO = new (user.getId());
//        return resultRO;

        if (StringUtils.isEmpty(unionId)) {
            throw new SocialParamsException("无效的内容标识1");
        }
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUuid(unionId);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识3");
        }
        return uniContentUnionIdDO.getId();
*//*
        //通用部分
        //判断uid有效
        Optional<UnionIdDO> contentUnionIdDOOptional = unionIdRepository.findFirstByUnionIdOrderByIdDesc(unionId);
        if (!contentUnionIdDOOptional.isPresent()) {
            throw new SocialParamsException("无效的内容标识");
        }

        UnionIdDO contentUnionIdDO = contentUnionIdDOOptional.get();
        //不为启用 或 如果已过期，已失效
        if (!contentUnionIdDO.getStatus().equals(CommonStatus.enable)) {
            throw new SocialBusinessException("内容已过期，请刷新");
        }

        //类型不对应
        if (!contentUnionIdDO.getContentType().equals(contentType)) {
            throw new SocialParamsException("无效的内容标识2");
        }

        Integer devId = DevAccountUtils.getDevIdNotNull();
        //有效，则校验商户id和userid是否一致
        //这里存在一个问题，有商户但是没用户的时候替换的问题
        //确认商户一致
        Integer unionDevId = contentUnionIdDO.getDevId();
        //商户号必须一致，任何情况，都未null或者都为有值
        if (!Objects.equals(devId, unionDevId)) {
            throw new SocialSystemException("有人商户秘钥被盗用了");
        }
        Integer unionUserId = contentUnionIdDO.getUserId();
        //数据库可以为null，存在未登录用户，替换登录后用户unionId的情况
        Integer userId = user == null ? null : user.getId();
        if (!(unionUserId == null || unionUserId.equals(userId))) {
            throw new SocialParamsException("错误的用户标示");
        }
        return contentUnionIdDO.getContentId();*//*
    }*/
}