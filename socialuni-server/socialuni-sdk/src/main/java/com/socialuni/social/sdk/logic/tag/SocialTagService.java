package com.socialuni.social.sdk.logic.tag;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tag.dao.redis.SocialTagRedis;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.sdk.logic.domain.SoicialTagAddDomain;
import com.socialuni.social.tag.model.TagRO;
import com.socialuni.social.tag.model.TagTypeRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
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