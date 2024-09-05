package com.socialuni.social.tance.dev.facade;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.NumberUtils;
import com.socialuni.social.common.api.utils.SnowflakeIdUtil;
import com.socialuni.social.tance.dev.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static Long createUserUnionIdOrGet(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.user, unionId);
    }

    public static Long createUserUnionId() {
        return createUnionIdByContentType(SocialuniContentType.user, SnowflakeIdUtil.nextId());
    }

    public static Long createChatUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.chat, unionId);
    }

    public static Long createChatUnionId() {
        return createUnionIdByContentType(SocialuniContentType.chat, SnowflakeIdUtil.nextId());
    }


    public static Long createMessageUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.message, unionId);
    }

    public static Long createUserImgUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.userImg, unionId);
    }

    public static Long createTalkImgUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.talkImg, unionId);
    }

    public static Long createTalkUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.talk, unionId);
    }

    public static Long createCommentUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.comment, unionId);
    }

    public static Long createPeiwanImgUnionId(String unionId) {
        return createUnionIdByContentType(SocialuniContentType.peiwanImg, unionId);
    }

    //自身创建
    /*private static Long createUnionIdByContentType(String contentType) {
        SocialuniUnionIdModler uniContentUnionIdDO = new SocialuniUnionIdModler(contentType, UUIDUtil.getSnowflakeId(), DevAccountFacade.getDevIdNullElseCenterDevId());
        uniContentUnionIdDO = socialuniUnionIdApi.savePut(uniContentUnionIdDO);
        //有的话更新
        return uniContentUnionIdDO.getUnionId();
    }*/
    //byUUid，是进入都是str
    private static Long createUnionIdByContentType(String contentType, Long unionId) {
        if (ObjectUtils.isEmpty(unionId)) {
            throw new SocialParamsException("参数错误202326");
        }
        SocialuniUnionIdDo uniContentUnionIdDO = getUnionByUuidAllowNull(unionId);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new SocialuniUnionIdDo(contentType, unionId, DevAccountFacade.getDevIdNullElseCenterDevId());
            uniContentUnionIdDO = socialuniUnionIdApi.savePut(uniContentUnionIdDO);
            //有的话更新
        }
        //有的话更新
        return uniContentUnionIdDO.getSelfSysId();
    }


    public static Long createUnionIdByUuid(String contentType, Long uuid) {
        if (ObjectUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标识3");
        }

        SocialuniUnionIdDo uniContentUnionIdDO = getUnionByUuidAllowNull(uuid);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new SocialuniUnionIdDo(contentType, uuid, DevAccountFacade.getCenterDevIdNotNull());
            socialuniUnionIdApi.savePut(uniContentUnionIdDO);
            //有的话更新
        }
        return uuid;
    }

    private static Long createUnionIdByContentType(String contentType, String uuid) {
        if (ObjectUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标识3");
        }
        if (NumberUtils.strHasNoNumber(uuid)) {
            throw new SocialParamsException("uuid错误1000320");
        }
        Long unionId = Long.valueOf(uuid);
        //有的话更新
        return createUnionIdByContentType(contentType, unionId);
    }

    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static void updateUuidByUnionIdNotNull(Long unionId, String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            throw new SocialParamsException("唯一标识异常100356");
        }
        SocialuniUnionIdDo uniContentUnionIdDO = getUnionDOByUnionIdNotNull(unionId);
        //没有写入
        uniContentUnionIdDO.setUnionId(Long.valueOf(uuid));
        socialuniUnionIdApi.savePut(uniContentUnionIdDO);
    }


    /*public static String getUuidByUnionIdNotNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }
        return getUuidByUnionIdNotNull(Long.valueOf(uuid));
    }*/

    //social层，根据unionId获取uid，不可为空
    public static String getUuidByUnionIdNotNull(Long unionId) {
        return String.valueOf(getUnionDOByUnionIdNotNull(unionId).getUnionId());
    }


    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static SocialuniUnionIdDo getUnionDOByUnionIdNotNull(Long unionId) {
        if (unionId == null) {
            throw new SocialParamsException("无效的内容标识4");
        }
        SocialuniUnionIdDo uniContentUnionIdDO = socialuniUnionIdApi.findById(Math.toIntExact(unionId));
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("无效的内容标识5");
        }
        return uniContentUnionIdDO;
    }

    public static SocialuniUnionIdDo getUnionDOByUnionIdAllowNull(Long unionId) {
        if (unionId == null) {
            return null;
        }
        SocialuniUnionIdDo uniContentUnionIdDO = socialuniUnionIdApi.findById(Math.toIntExact(unionId));
        if (uniContentUnionIdDO == null) {
            return null;
        }
        return uniContentUnionIdDO;
    }


    //结果不可为空 ，为前台传入的数据,根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static Long getUnionIdByUuidNotNull(Long uuid) {
        return getUnionByUuidNotNull(uuid).getSelfSysId();
    }

    public static Long getUnionIdByUuidNotNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }
        Long unionId = Long.valueOf(uuid);
        return getUnionByUuidNotNull(unionId).getSelfSysId();
    }


    public static Long getChatUnionIdByUuidNotNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }
        Long unionId = Long.valueOf(uuid);
        return getChatUnionIdByUuidNotNull(unionId);
    }


    public static Long getChatUnionIdByUuidNotNull(Long uuid) {
        SocialuniUnionIdDo chatUnion = getUnionByUuidNotNull(uuid);
        if (!Arrays.asList(SocialuniContentType.chat, SocialuniContentType.user).contains(chatUnion.getContentType())) {
            throw new SocialSystemException("不存在的会话");
        }
        return chatUnion.getSelfSysId();
    }

    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static SocialuniUnionIdDo getUnionByUuidNotNull(Long uuid) {
        SocialuniUnionIdDo uniContentUnionIdDO = getUnionByUuidAllowNull(uuid);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识2：" + uuid);
        }
        return uniContentUnionIdDO;
    }

    public static SocialuniUnionIdDo getUnionByUuidNotNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }
        Long unionId = Long.valueOf(uuid);
        return getUnionByUuidNotNull(unionId);
    }

    //外部使用可能查询不存在的
    public static SocialuniUnionIdDo getUnionByUuidAllowNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }
        Long unionId = Long.valueOf(uuid);
        return getUnionByUuidAllowNull(unionId);
    }

    //外部使用可能查询不存在的
    public static SocialuniUnionIdDo getUnionByUuidAllowNull(Long uuid) {
        if (ObjectUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标识1:" + uuid);
        }
        return socialuniUnionIdApi.findByUuId(uuid);
    }

    public static Long getUnionIdByUuidAllowNull(String uuid) {
        if (NumberUtils.strHasNoNumber(uuid)) {
            return null;
        }

        Long unionId = Long.valueOf(uuid);

        return getUnionIdByUuidAllowNull(unionId);
    }


    public static Long getUnionIdByUuidAllowNull(Long uuid) {
        SocialuniUnionIdDo socialuniUnionIdDo = SocialuniUnionIdFacede.getUnionByUuidAllowNull(uuid);
        if (socialuniUnionIdDo == null) {
            return null;
        }
        return socialuniUnionIdDo.getSelfSysId();
    }


    public static List<Long> getContentIdsByTalkUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUuIds(contentUnionIds, SocialuniContentType.talk);
    }

    public static List<Long> getContentIdsByUserUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUuIds(contentUnionIds, SocialuniContentType.user);
    }

    public static List<Long> getContentIdsByUserImgUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUuIds(contentUnionIds, SocialuniContentType.userImg);
    }

    public static List<Long> getContentIdsByCommentUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUuIds(contentUnionIds, SocialuniContentType.comment);
    }

    public static List<Long> getContentIdsByMessageUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUuIds(contentUnionIds, SocialuniContentType.message);
    }

    public static List<Long> getContentIdsByUuIds(List<String> contentUnionIds, String contentType) {
        List<Long> ids = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(contentUnionIds)) {
            for (String uuid : contentUnionIds) {
//                log.info("查询单个id：" + System.currentTimeMillis());
                Long id = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(uuid);
                ids.add(id);
            }
        }
        if (ids.isEmpty()) {
            //必须使用这个，后续需要往里面add元素
            ids = new ArrayList<>();
            ids.add(0L);
//            ids = null;
        }
        return ids;
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static List<Long> findUuidAllByContentType(String contentTyp) {
        return socialuniUnionIdApi.findUuidAllByContentType(contentTyp);
    }

    public static List<Long> findAllIdsByContentType(String contentTyp) {
        return socialuniUnionIdApi.findAllUnionIdsByContentType(contentTyp);
    }


    //根据uid获取真实id
    /*public static Long getResultByUnionId(String contentType, String unionId) {
//        Long resultRO = new (user.getId());
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

        Long devId = DevAccountUtils.getDevIdNotNull();
        //有效，则校验商户id和userid是否一致
        //这里存在一个问题，有商户但是没用户的时候替换的问题
        //确认商户一致
        Long unionDevId = contentUnionIdDO.getDevId();
        //商户号必须一致，任何情况，都未null或者都为有值
        if (!Objects.equals(devId, unionDevId)) {
            throw new SocialSystemException("有人商户秘钥被盗用了");
        }
        Long unionUserId = contentUnionIdDO.getUserId();
        //数据库可以为null，存在未登录用户，替换登录后用户unionId的情况
        Long userId = user == null ? null : user.getId();
        if (!(unionUserId == null || unionUserId.equals(userId))) {
            throw new SocialParamsException("错误的用户标示");
        }
        return contentUnionIdDO.getContentId();*//*
    }*/
}