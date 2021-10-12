package com.socialuni.social.sdk.service;

import com.socialuni.social.sdk.domain.tag.SoicialTagAddDomain;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.store.SocialTagRedis;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialTagService {
    @Resource
    SocialTagRedis socialTagRedis;

    @Resource
    SoicialTagAddDomain soicialTagAddDomain;

    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();
        TagRO tagRO = soicialTagAddDomain.addTag(mineUser, tagAddQO);
        return new ResultRO<>(tagRO);
    }

    public ResultRO<List<TagRO>> queryTags() {
        List<TagRO> tags = socialTagRedis.getAllTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagRO>> queryHotTags() {
        List<TagRO> tags = socialTagRedis.getHotTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        List<TagTypeRO> tags = socialTagRedis.getAllTageTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        List<TagTypeRO> tags = socialTagRedis.getHotTagTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }
}