package com.socialuni.sdk.serive;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.feignAPI.SocialuniTagAPI;
import com.socialuni.sdk.utils.CenterUserUtil;
import com.socialuni.sdk.domain.tag.SoicialTagAddDomain;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
import com.socialuni.sdk.model.RO.community.tag.TagTypeRO;
import com.socialuni.sdk.store.SocialTagRedis;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterTagService {
    @Resource
    SocialTagRedis socialTagRedis;

    @Resource
    SoicialTagAddDomain soicialTagAddDomain;

    @Resource
    SocialuniTagAPI socialuniTagAPI;

    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        TagRO tagRO = soicialTagAddDomain.addTag(mineUser, tagAddQO);

        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTagAPI.addTag(tagAddQO);
        }

        return new ResultRO<>(tagRO);
    }

    public ResultRO<List<TagRO>> queryTags() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTagAPI.queryTags();
        }
        List<TagRO> tags = socialTagRedis.getAllTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }

    public ResultRO<List<TagRO>> queryHotTags() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTagAPI.queryHotTags();
        }
        List<TagRO> tags = socialTagRedis.getHotTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTagAPI.queryTagTypes();
        }
        List<TagTypeRO> tags = socialTagRedis.getAllTageTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniTagAPI.queryHotTagTypes();
        }
        List<TagTypeRO> tags = socialTagRedis.getHotTagTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }
}