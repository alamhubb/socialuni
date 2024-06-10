package com.socialuni.social.sdk.logic.tag;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tag.dao.redis.SocialTagRedis;
import com.socialuni.social.tag.api.SocialuniTagAPI;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.tag.logic.SoicialTagAddDomain;
import com.socialuni.social.tag.model.TagRO;
import com.socialuni.social.tag.model.TagTypeRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniTagService {
    @Resource
    SocialTagRedis socialTagRedis;

    @Resource
    SoicialTagAddDomain soicialTagAddDomain;

    @Resource
    SocialuniTagAPI socialuniTagAPI;

    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        TagRO tagRO = soicialTagAddDomain.addTag(mineUser, tagAddQO);

        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTagAPI.addTag(tagAddQO);
        }

        return new ResultRO<>(tagRO);
    }

    public ResultRO<List<TagRO>> queryTags() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTagAPI.queryTags();
        }
        List<TagRO> tags = socialTagRedis.getAllTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }

    public ResultRO<List<TagRO>> queryHotTags() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTagAPI.queryHotTags();
        }
        List<TagRO> tags = socialTagRedis.getHotTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTagAPI.queryTagTypes();
        }
        List<TagTypeRO> tags = socialTagRedis.getAllTageTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniTagAPI.queryHotTagTypes();
        }
        List<TagTypeRO> tags = socialTagRedis.getHotTagTypesRedis(GenderType.all);
        return new ResultRO<>(tags);
    }
}