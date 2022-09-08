package com.socialuni.sdk.utils;

import com.socialuni.sdk.dao.DO.UniContentUnionIdDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.UniContentUnionIdRepository;
import com.socialuni.sdk.dao.repository.UnionIdRepository;
import com.socialuni.sdk.dao.store.UnionIdStore;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
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
public class UnionIdDbUtil {
    private static UnionIdRepository unionIdRepository;
    private static UnionIdStore unionIdStore;

    private static UniContentUnionIdRepository uniContentUnionIdRepository;

    @Resource
    public void setUniContentUnionIdRepository(UniContentUnionIdRepository uniContentUnionIdRepository) {
        UnionIdDbUtil.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }

    @Resource
    public void setUnionIdRepository(UnionIdRepository unionIdRepository) {
        UnionIdDbUtil.unionIdRepository = unionIdRepository;
    }

    @Resource
    public void setUnionIdStore(UnionIdStore unionIdStore) {
        UnionIdDbUtil.unionIdStore = unionIdStore;
    }

    //根据空的创建， 本系统写入数据时，需要先创建，然后写入内容表unionId，然后再根据返回内容更新uid
    public static Integer createUserUnionId() {
        return createUnionIdByContentType(ContentType.user);
    }

    public static Integer createUserImgUnionId() {
        return createUnionIdByContentType(ContentType.userImg);
    }

    public static Integer createTalkUnionId() {
        return createUnionIdByContentType(ContentType.talk);
    }

    public static Integer createCommentUnionId() {
        return createUnionIdByContentType(ContentType.comment);
    }

    //自身创建
    private static Integer createUnionIdByContentType(String contentType) {
        UniContentUnionIdDO uniContentUnionIdDO = new UniContentUnionIdDO(contentType, UUIDUtil.getUUID(), DevAccountUtils.getDevIdNotNull());
        uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        //有的话更新
        return uniContentUnionIdDO.getId();
    }

    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static void updateUidByUnionIdNotNull(Integer unionId, String uuid) {
        UniContentUnionIdDO uniContentUnionIdDO = getUnionDOByUnionIdNotNull(unionId);
        //没有写入
        uniContentUnionIdDO.setUuid(uuid);
        uniContentUnionIdRepository.save(uniContentUnionIdDO);
    }

    //social层，根据unionId获取uid，不可为空
    public static String getUidByUnionIdNotNull(Integer unionId) {
        return getUnionDOByUnionIdNotNull(unionId).getUuid();
    }

    //空的创建的，然后更新，只有往中心推送后，可调用这里更新
    public static UniContentUnionIdDO getUnionDOByUnionIdNotNull(Integer unionId) {
        if (unionId == null) {
            throw new SocialParamsException("无效的内容标示4");
        }
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findOneById(unionId);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("无效的内容标示5");
        }
        return uniContentUnionIdDO;
    }

    //获取可为空， 将中心的数据写入本系统
    public static String createTalkUidByUid(String talkUid) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return createUidByUid(ContentType.talk, talkUid);
    }

    public static String createUidByUid(String contentType, String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标示3");
        }
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUuid(uuid);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, uuid, DevAccountUtils.getCenterDevIdNotNull());
            uniContentUnionIdRepository.save(uniContentUnionIdDO);
            //有的话更新
        }
        return uuid;
    }


    //结果不可为空 ，为前台传入的数据,根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static Integer getUserUnionIdByUidNotNull(String uid) {
        return getUnionIdByUidNotNull(uid);
    }

    public static Integer getTalkUnionIdByUidNotNull(String uid) {
        return getUnionIdByUidNotNull(uid);
    }

    public static Integer getUserImgUnionIdByUidNotNull(String uid) {
        return getUnionIdByUidNotNull(uid);
    }

    public static Integer getCommentUnionIdByUidNotNull(String uid) {
        return getUnionIdByUidNotNull(uid);
    }

    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static Integer getUnionIdByUidNotNull(String uuid) {
        return getUnionByUidNotNull(uuid).getId();
    }

    //根据uid获取真实id,获取不可为空, 为前台传入的数据，防止错误，不可为空
    public static UniContentUnionIdDO getUnionByUidNotNull(String uuid) {
        UniContentUnionIdDO uniContentUnionIdDO = getUnionByUidAllowNull(uuid);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识2");
        }
        return uniContentUnionIdDO;
    }

    //外部使用可能查询不存在的
    public static UniContentUnionIdDO getUnionByUidAllowNull(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new SocialParamsException("无效的内容标示1");
        }
        return uniContentUnionIdRepository.findByUuid(uuid);
    }


    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds) {
        SocialuniUserDO user = SocialuniUserUtil.getMineUserAllowNull();
        return getContentIdsByUnionIds(contentUnionIds, ContentType.talk);
    }

    public static List<Integer> getContentIdsByUserUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.user);
    }

    public static List<Integer> getContentIdsByUserImgUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.userImg);
    }

    public static List<Integer> getContentIdsByCommentUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.comment);
    }

    public static List<Integer> getContentIdsByMessageUnionIds(List<String> contentUnionIds) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.message);
    }

    public static List<Integer> getContentIdsByUnionIds(List<String> contentUnionIds, String contentType) {
        List<Integer> ids = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(contentUnionIds)) {
            for (String uuid : contentUnionIds) {
//                log.info("查询单个id：" + System.currentTimeMillis());
                Integer id = UnionIdDbUtil.getUnionIdByUidNotNull(uuid);
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
            throw new SocialParamsException("无效的内容标示1");
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
            throw new SocialParamsException("无效的内容标示");
        }

        UnionIdDO contentUnionIdDO = contentUnionIdDOOptional.get();
        //不为启用 或 如果已过期，已失效
        if (!contentUnionIdDO.getStatus().equals(CommonStatus.enable)) {
            throw new SocialBusinessException("内容已过期，请刷新");
        }

        //类型不对应
        if (!contentUnionIdDO.getContentType().equals(contentType)) {
            throw new SocialParamsException("无效的内容标示2");
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