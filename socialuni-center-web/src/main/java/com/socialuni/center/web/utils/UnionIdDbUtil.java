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

    public static String getUnionIdByContentTypeAndContentId(String contentType, Integer contentId) {
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByContentTypeAndContentId(contentType, contentId);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, contentId, UUIDUtil.getUUID());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            //有的话更新
        }
        return uniContentUnionIdDO.getUnionId();
    }

    //只有往中心推送后，可调用这里更新
    public static void updateUnionIdByContentTypeAndContentId(String contentType, Integer contentId, String unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByContentTypeAndContentId(contentType, contentId);
        //没有写入
        if (uniContentUnionIdDO == null) {
            uniContentUnionIdDO = new UniContentUnionIdDO(contentType, contentId, unionId);
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
            //有的话更新
        } else {
            uniContentUnionIdDO.setUnionId(unionId);
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        }
    }

    public static Integer getContentId(String unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUnionId(unionId);
        //没有写入
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识");
            //有的话更新
        }
        return uniContentUnionIdDO.getContentId();
    }

    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds) {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        return getContentIdsByUnionIds(contentUnionIds, ContentType.talk, user);
    }

    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds, SocialUserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.talk, user);
    }

    public static List<Integer> getContentIdsByUserUnionIds(List<String> contentUnionIds, SocialUserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.user, user);
    }

    public static List<Integer> getContentIdsByUserImgUnionIds(List<String> contentUnionIds, SocialUserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.userImg, user);
    }

    public static List<Integer> getContentIdsByCommentUnionIds(List<String> contentUnionIds, SocialUserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.comment, user);
    }

    public static List<Integer> getContentIdsByMessageUnionIds(List<String> contentUnionIds, SocialUserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.message, user);
    }

    public static List<Integer> getContentIdsByUnionIds(List<String> contentUnionIds, String contentType, SocialUserDO user) {
        List<Integer> ids = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(contentUnionIds)) {
            for (String contentUnionId : contentUnionIds) {
//                log.info("查询单个id：" + System.currentTimeMillis());
                Integer id = UnionIdDbUtil.getResultByUnionId(contentType, contentUnionId, user);
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


    private static String addUnionIdDO(String contentType, Integer contentId, SocialUserDO user) {
        //如果这两个都不为空则查找是否存在有效的
        Integer userId = null;
        if (user != null) {
            userId = user.getId();
        }
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, DevAccountUtils.getDevIdNotNull());
    }

    private static String addUnionIdDO(String contentType, Integer contentId, SocialUserDO user, Integer devId) {
        //如果这两个都不为空则查找是否存在有效的
        Integer userId = null;
        if (user != null) {
            userId = user.getId();
        }
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, devId);
    }

    //针对所有外部的数据，内部的数据，生成unionId。

    private static String addUnionIdDO(String contentType, Integer contentId, Integer userId) {
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, DevAccountUtils.getDevIdNotNull());
    }

    private static String addUnionIdDO(String contentType, Integer contentId, Integer userId, Integer devId) {

//        uniContentUnionIdRepository.findByDataDevIdAndDataContentUnionId()

        return contentId.toString();
//        return contentId.toString();
        /*Date curDate = new Date();
//        log.info("创建uniond1：" + SystemUtil.getCurrentTimeSecond());
        //如果这两个都不为空则查找是否存在有效的
        //每天0点到现在不能发布超过10条

        //默认为初始时间
        Date lastTenMinutes = new Date(0);
        //如果有有效的，返回有效的，如果没有有效的，新建.
        //查询可用的可以保证没缓存的情况下查询的也是可用的
        if (!ObjectUtils.allNotNull(userId, devId)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            int minute = calendar.get(Calendar.MINUTE);
            //获取前最近的10分钟整
            int ageMinuteTen = (minute / 10) * 10;
            calendar.set(Calendar.MINUTE, ageMinuteTen);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            lastTenMinutes = calendar.getTime();
        }
        UnionIdDO unionIdDO = unionIdRepository.findFirstByContentTypeAndContentIdAndStatusAndDevIdAndUserIdAndBeginTimeOrderByIdDesc(contentType, contentId, CommonStatus.enable, devId, userId, lastTenMinutes);
        //如果不为null
        if (unionIdDO != null) {
            //如果都不为空
            if (ObjectUtils.allNotNull(userId, devId)) {
                return unionIdDO.getUnionId();
            }
            //失效时间大于当前事件，还未失效
            if (unionIdDO.getExpiredTime().getTime() > curDate.getTime()) {
                return unionIdDO.getUnionId();
            } else {
                //不存在这种逻辑，因为只能读10分钟内的，10分钟内不会超时，所以通过程序循环计时器删除
                //已失效，修改状态
                unionIdDO.setStatus(CommonStatus.delete);
                //更新状态
                unionIdDO.setUpdateTime(curDate);
//                log.info("创建uniond4：" + SystemUtil.getCurrentTimeSecond());
                unionIdStore.saveAsync(unionIdDO);
            }
        }
        //得到最近的之前的10分钟，创建的时候，用10分钟内的时间+30分钟,生成过期时间
        //不存在已有的
        String uuid = UUIDUtil.getUUID();

        unionIdDO = UnionIdDOFactory.createUnionDO(contentType, contentId, devId, curDate, lastTenMinutes, uuid, userId);

//        log.info("创建uniond5：" + SystemUtil.getCurrentTimeSecond());
//        log.info("创建uniond：" + unionIdDO.getUnionId());
        unionIdStore.saveAsync(unionIdDO);
//        log.info("创建uniond6：" + SystemUtil.getCurrentTimeSecond());
        return unionIdDO.getUnionId();*/
    }

    public static Integer getResultByUnionId(String contentType, String unionId) {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        return UnionIdDbUtil.getResultByUnionId(contentType, unionId, user);
    }

    //根据uid获取真实id
    public static Integer getResultByUnionId(String contentType, String unionId, SocialUserDO user) {
//        Integer resultRO = new (user.getId());
//        return resultRO;

        if (StringUtils.isEmpty(unionId)) {
            throw new SocialParamsException("无效的内容标示1");
        }
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByUnionId(unionId);
        if (uniContentUnionIdDO == null) {
            throw new SocialParamsException("错误的内容标识3");
        }
        return uniContentUnionIdDO.getContentId();
/*
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
        return contentUnionIdDO.getContentId();*/
    }


    public static Integer getTalkIdByUnionId(String unionId, SocialUserDO user) {
        return getResultByUnionId(ContentType.talk, unionId, user);
    }

    public static Integer getTalkIdByUnionId(String unionId) {
        return getResultByUnionId(ContentType.talk, unionId);
    }

    public static Integer getCommentIdByUid(String unionId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.comment, unionId, user);
    }

    public static Integer getCommentIdByUid(String unionId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.comment, unionId);
    }

    public static Integer getUserIdByUid(String unionId) {
        SocialUserDO user = CenterUserUtil.getMineUserAllowNull();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.user, unionId, user);
    }

    public static Integer getUserIdByUid(String unionId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.user, unionId, user);
    }

    public static Integer getUserImgIdByUid(String unionId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.userImg, unionId, user);
    }

    public static Integer getUserImgIdByUid(String unionId) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.userImg, unionId, mineUser);
    }

    public static Integer getMessageIdByUid(String unionId, SocialUserDO user) {
        Integer messageIdResult = getResultByUnionId(ContentType.message, unionId, user);
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return messageIdResult;
    }

    //如果包含用户id和devid，则先查询，是否已经有了，
    //否则新建
    public static String createTalkUid(Integer modeId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, user);
    }

    public static String createTalkUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, userId);
    }

    /*public static String createTalkUid(Integer modeId) {
        Integer mineId = CenterUserUtil.getMineUserIdAllowNull();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, mineId);
    }*/

    public static String createTalkUid(Integer talkId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.talk, talkId);
    }

    public static String createCommentUid(Integer commentId) {
        return getUnionIdByContentTypeAndContentId(ContentType.comment, commentId);
    }

    public static String createCommentUid(String modeId) {
        Integer mineId = CenterUserUtil.getMineUserIdAllowNull();
        return getUnionIdByContentTypeAndContentId(ContentType.comment, Integer.valueOf(modeId));
    }

    public static String createCommentUid(Integer modeId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.comment, modeId);
    }

    public static String createCommentUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.comment, modeId);
    }

    public static String createUserUid(Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.user, userId);
    }

    public static String addUnionIdDO(String contentType, String contentId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(contentType, Integer.valueOf(contentId), userId);
    }

    public static String createTalkImgUid(Integer contentId) {
        Integer mineId = CenterUserUtil.getMineUserIdAllowNull();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.talkImg, contentId);
    }

    public static String createUserImgUid(Integer modeId, SocialUserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getUnionIdByContentTypeAndContentId(ContentType.userImg, modeId);
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}