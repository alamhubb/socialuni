package com.socialuni.sdk.utils;

import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.platform.UniappProviderType;
import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import com.socialuni.sdk.dao.mapper.TalkImgMapper;
import com.socialuni.sdk.dao.repository.community.TalkImgRepository;
import com.socialuni.social.web.sdk.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class TalkImgDOUtils {
    private static TalkImgRepository talkImgRepository;
    private static TalkImgMapper talkImgMapper;

    @Resource
    public void setTalkImgMapper(TalkImgMapper talkImgMapper) {
        TalkImgDOUtils.talkImgMapper = talkImgMapper;
    }

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        TalkImgDOUtils.talkImgRepository = talkImgRepository;
    }


    //根据id列表从缓存中读取talk列表
    public static List<SocialTalkImgDO> findTop3ByTalkId(Integer talkId) {
        return talkImgRepository.findTop3ByContentId(talkId);
    }

    //根据id列表从缓存中读取talk列表
    public static List<SocialTalkImgDO> findTop3ByTalkUid(Integer talkId) {
        Boolean hasPeopleImgTalkNeedIdentity = SocialuniAppConfig.getAppConfig().getDisableUnderageContent();
        //qq渠道默认查询认证的用户内容
        Boolean hasPeopleImgTalkNeedIdentityCopy = null;
        if (hasPeopleImgTalkNeedIdentity || UniappProviderType.qq.equals(RequestUtil.getProvider())) {
            hasPeopleImgTalkNeedIdentityCopy = true;
        }
        return talkImgMapper.queryTalkImgsTop3ByTalkUidOrderByIdAsc(talkId, hasPeopleImgTalkNeedIdentityCopy);
    }
}