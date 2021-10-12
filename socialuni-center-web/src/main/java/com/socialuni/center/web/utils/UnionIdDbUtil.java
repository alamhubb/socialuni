package com.socialuni.center.web.utils;

import com.socialuni.center.sdk.mode.DevAccountDO;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.factory.DO.UnionIdDOFactory;
import com.socialuni.center.web.model.DO.UnionIdDO;
import com.socialuni.center.web.repository.UnionIdRepository;
import com.socialuni.center.web.store.UnionIdStore;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.comment.CommentDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.utils.ObjectUtil;
import com.socialuni.social.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    public void setUnionIdRepository(UnionIdRepository unionIdRepository) {
        UnionIdDbUtil.unionIdRepository = unionIdRepository;
    }

    @Resource
    public void setUnionIdStore(UnionIdStore unionIdStore) {
        UnionIdDbUtil.unionIdStore = unionIdStore;
    }

    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds) {
        UserDO user = CenterUserUtil.getMineUser();
        return getContentIdsByUnionIds(contentUnionIds, ContentType.talk, user);
    }

    public static List<Integer> getContentIdsByTalkUnionIds(List<String> contentUnionIds, UserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.talk, user);
    }

    public static List<Integer> getContentIdsByUserUnionIds(List<String> contentUnionIds, UserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.user, user);
    }

    public static List<Integer> getContentIdsByUserImgUnionIds(List<String> contentUnionIds, UserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.userImg, user);
    }

    public static List<Integer> getContentIdsByCommentUnionIds(List<String> contentUnionIds, UserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.comment, user);
    }

    public static List<Integer> getContentIdsByMessageUnionIds(List<String> contentUnionIds, UserDO user) {
        return getContentIdsByUnionIds(contentUnionIds, ContentType.message, user);
    }

    public static List<Integer> getContentIdsByUnionIds(List<String> contentUnionIds, String contentType, UserDO user) {
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


    private static String addUnionIdDO(String contentType, Integer contentId, UserDO user) {
        //如果这两个都不为空则查找是否存在有效的
        Integer userId = null;
        if (user != null) {
            userId = user.getId();
        }
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, DevAccountUtils.getDevId());
    }

    private static String addUnionIdDO(String contentType, Integer contentId, UserDO user, Integer devId) {
        //如果这两个都不为空则查找是否存在有效的
        Integer userId = null;
        if (user != null) {
            userId = user.getId();
        }
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, devId);
    }

    private static String addUnionIdDO(String contentType, Integer contentId, Integer userId) {
        return UnionIdDbUtil.addUnionIdDO(contentType, contentId, userId, DevAccountUtils.getDevId());
    }

    private static String addUnionIdDO(String contentType, Integer contentId, Integer userId, Integer devId) {
//        return contentId.toString();
        Date curDate = new Date();
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
        return unionIdDO.getUnionId();
    }

    public static Integer getResultByUnionId(String contentType, String unionId) {
        UserDO user = CenterUserUtil.getMineUser();
        return UnionIdDbUtil.getResultByUnionId(contentType, unionId, user);
    }

    //根据uid获取真实id
    public static Integer getResultByUnionId(String contentType, String unionId, UserDO user) {
//        Integer resultRO = new (user.getId());
//        return resultRO;

        if (StringUtils.isEmpty(unionId)) {
            throw new SocialParamsException("无效的内容标示1");
        }
        //todo 这里直接返回了
        //存在数据库性能问题，使用真实id，兼容旧版本
        if (UnionIdDbUtil.isInteger(unionId)) {
            return Integer.valueOf(unionId);
        }

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

        Integer devId = DevAccountUtils.getDevId();
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
        return contentUnionIdDO.getContentId();
    }


    public static Integer getTalkIdByUnionId(String unionId, UserDO user) {
        return getResultByUnionId(ContentType.talk, unionId, user);
    }

    public static Integer getTalkIdByUnionId(String unionId) {
        return getResultByUnionId(ContentType.talk, unionId);
    }

    public static Integer getCommentIdByUid(String unionId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.comment, unionId, user);
    }

    public static Integer getCommentIdByUid(String unionId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.comment, unionId);
    }

    public static Integer getUserIdByUid(String unionId) {
        UserDO user = CenterUserUtil.getMineUser();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.user, unionId, user);
    }

    public static Integer getUserIdByUid(String unionId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.user, unionId, user);
    }

    public static Integer getUserImgIdByUid(String unionId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.userImg, unionId, user);
    }

    public static Integer getUserImgIdByUid(String unionId) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return getResultByUnionId(ContentType.userImg, unionId, mineUser);
    }

    public static Integer getMessageIdByUid(String unionId, UserDO user) {
        Integer messageIdResult = getResultByUnionId(ContentType.message, unionId, user);
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return messageIdResult;
    }

    //如果包含用户id和devid，则先查询，是否已经有了，
    //否则新建
    public static String createTalkUid(Integer modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, user);
    }

    public static String createTalkUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, userId);
    }

    public static String createTalkUid(Integer modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talk, modeId, mineId);
    }

    public static String createCommentUid(CommentDO commentDO) {
        return createCommentUid(commentDO.getId());
    }

    public static String createCommentUid(Integer modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        return createCommentUid(modeId, mineId);
    }

    public static String createCommentUid(String modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        return addUnionIdDO(ContentType.comment, modeId, mineId);
    }

    public static String createCommentUid(Integer modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.comment, modeId, user);
    }

    public static String createCommentUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.comment, modeId, userId);
    }

    public static String createUserUid(Integer modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, mineId);
    }

    public static String createUserUid(String modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, mineId);
    }

    public static String createUserUid(Integer modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, user);
    }

    public static String createUserUid(Integer modeId, UserDO user, DevAccountDO devAccountDO) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        //如果这两个都不为空则查找是否存在有效的
        return UnionIdDbUtil.addUnionIdDO(ContentType.user, modeId, user.getId(), devAccountDO.getId());
    }

    public static String addUnionIdDO(String contentType, String contentId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(contentType, Integer.valueOf(contentId), userId);
    }

    public static String createTalkImgUid(Integer contentId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.talkImg, Integer.valueOf(contentId), mineId);
    }

    public static String createUserUid(String modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, Integer.valueOf(modeId), user);
    }

    public static String createUserUid(Integer modeId, UserDO user, Integer devId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, user, devId);
    }

    public static String createUserUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, userId);
    }

    public static String createUserUid(Integer modeId, Integer userId, Integer devId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.user, modeId, userId, devId);
    }

    public static String createUserImgUid(String modeId) {
        Integer mineId = CenterUserUtil.getMineUserId();
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.userImg, modeId, mineId);
    }

    public static String createUserImgUid(Integer modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.userImg, modeId, user);
    }

    public static String createUserImgUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.userImg, modeId, userId);
    }

    public static String createMessageUid(Integer modeId, UserDO user) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.message, modeId, user);
    }

    public static String createMessageUid(Integer modeId, Integer userId) {
        //需要设置有效期，根据查询类型，，设置的还要看是不是已经有有效的了？再次查询无论如何都生成旧的，以前的就不管了
        return addUnionIdDO(ContentType.message, modeId, userId);
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}