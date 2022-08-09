package com.socialuni.center.web.utils;

import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.repository.UnionIdRepository;
import com.socialuni.center.web.store.UnionIdStore;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.web.sdk.utils.UUIDUtil;
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

    public static String getUuidByContentTypeAndContentId(String contentType, Integer unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = getUnionIdDOByContentTypeAndContentId(contentType, unionId);
        return uniContentUnionIdDO.getUuid();
    }


    public static Integer createUserUuid() {
        return getUnionIdByCreate(ContentType.user);
    }
    public static Integer createTalkUuid() {
        return getUnionIdByCreate(ContentType.talk);
    }

    public static Integer createCommentUuid() {
        return getUnionIdByCreate(ContentType.comment);
    }

    private static Integer getUnionIdByCreate(String contentType) {
        UniContentUnionIdDO uniContentUnionIdDO = getUnionIdDOByContentTypeAndContentId(contentType, null);
        return uniContentUnionIdDO.getId();
    }

    private static UniContentUnionIdDO getUnionIdDOByContentTypeAndContentId(String contentType, Integer unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = null;
        if (unionId != null) {
            uniContentUnionIdDO = uniContentUnionIdRepository.findOneById(unionId);
        }
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, UUIDUtil.getUUID(), DevAccountUtils.getDevIdNotNull());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            //有的话更新
        }
        return uniContentUnionIdDO;
    }

    //只有往中心推送后，可调用这里更新
    public static void updateUnionIdByContentTypeAndContentId(String contentType, Integer contentId, String unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findOneById(contentId);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, unionId, DevAccountUtils.getDevIdNotNull());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            //有的话更新
        } else {
            uniContentUnionIdDO.setUuid(unionId);
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        }
    }

    public static Integer getContentId(String uuid) {
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUuid(uuid);
        //没有写入
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识");
            //有的话更新
        }
        return uniContentUnionIdDO.getId();
    }

    public static List<Integer> getDOIdsByTalkUnionIds(List<String> contentUnionIds) {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        return getDOIdsByUnionIds(contentUnionIds);
    }

    public static List<Integer> getIdsByUserUnionIds(List<String> contentUnionIds) {
        return getDOIdsByUnionIds(contentUnionIds);
    }

    public static List<Integer> getIdsByUserImgUnionIds(List<String> contentUnionIds) {
        return getDOIdsByUnionIds(contentUnionIds);
    }

    public static List<Integer> getIdsByCommentUnionIds(List<String> contentUnionIds) {
        return getDOIdsByUnionIds(contentUnionIds);
    }

    public static List<Integer> getIdsByMessageUnionIds(List<String> contentUnionIds) {
        return getDOIdsByUnionIds(contentUnionIds);
    }

    public static List<Integer> getDOIdsByUnionIds(List<String> contentUnionIds) {
        List<Integer> ids = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(contentUnionIds)) {
            for (String contentUnionId : contentUnionIds) {
//                log.info("查询单个id：" + System.currentTimeMillis());
                Integer id = UnionIdDbUtil.getDOIdByUnionId(contentUnionId);
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


    public static Integer getMessageIdByUid(String unionId) {
        Integer messageIdResult = getDOIdByUnionId(unionId);
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return messageIdResult;
    }


    //根据uid获取真实id
    public static Integer getUnionIdByUuid(String contentType, String unionId) {
//        Integer resultRO = new (user.getId());
//        return resultRO;
        if (StringUtils.isEmpty(unionId)) {
            throw new SocialParamsException("无效的内容标示1");
        }
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUnionId(unionId);
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, null, unionId, DevAccountUtils.getCenterDevIdNotNull());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        }
        return uniContentUnionIdDO.getId();
    }


    public static Integer getTalkUnionIdByUnionId(String unionId) {
        return getUnionIdByUuid(ContentType.talk, unionId);
    }

    public static Integer getUserUnionIdByUid(String unionId) {
        return getUnionIdByUuid(ContentType.user, unionId);
    }

    public static Integer getUserImgUnionIdByUid(String unionId) {
        return getUnionIdByUuid(ContentType.userImg, unionId);
    }

    public static Integer getCommentUnionIdByUid(String unionId) {
        return getUnionIdByUuid(ContentType.comment, unionId);
    }


    //根据uid获取真实id
    public static Integer getDOIdByUnionId(String unionId) {
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
    }

    public static Integer getTalkDOIdByUnionId(String unionId) {
        return getDOIdByUnionId(unionId);
    }

    public static Integer getUserDOIdByUid(String unionId) {
        return getDOIdByUnionId(unionId);
    }

    public static Integer getUserImgDOIdByUid(String unionId) {
        return getDOIdByUnionId(unionId);
    }

    public static Integer getCommentDOIdByUid(String unionId) {
        return getDOIdByUnionId(unionId);
    }




    public static String createTalkUid(Integer talkId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUuidByContentTypeAndContentId(ContentType.talk, talkId);
    }

    public static String createCommentUid(Integer commentId) {
        return getUuidByContentTypeAndContentId(ContentType.comment, commentId);
    }

    public static String createUserUid(Integer unionId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUuidByContentTypeAndContentId(ContentType.user, unionId);
    }


    public static String createTalkImgUid(Integer contentId) {
        Integer mineId = CenterUserUtil.getMineUserIdAllowNull();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUuidByContentTypeAndContentId(ContentType.talkImg, contentId);
    }

    public static String createUserImgUid(Integer modeId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUuidByContentTypeAndContentId(ContentType.userImg, modeId);
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}