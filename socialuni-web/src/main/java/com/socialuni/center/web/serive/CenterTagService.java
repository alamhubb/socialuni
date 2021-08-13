package com.socialuni.center.web.serive;

import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.sdk.domain.tag.SoicialTagAddDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.store.SocialTagStore;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.model.model.RO.community.tag.TagTypeRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterTagService {
    @Resource
    SocialTagStore socialTagStore;

    @Resource
    SoicialTagAddDomain soicialTagAddDomain;


    public ResultRO<TagRO> addTag(TagAddQO tagAddQO) {
        UserDO mineUser = CenterUserUtil.getMineUser();
        TagRO tagRO = soicialTagAddDomain.addTag(mineUser, tagAddQO);
        return new ResultRO<>(tagRO);
    }

    public ResultRO<List<TagRO>> queryTags() {
        List<TagRO> tags = socialTagStore.getAllTagsRedis(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagRO>> queryHotTags() {
        List<TagRO> tags = socialTagStore.getHotTags(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryTagTypes() {
        List<TagTypeRO> tags = socialTagStore.getAllTageTypes(GenderType.all);
        return new ResultRO<>(tags);
    }


    public ResultRO<List<TagTypeRO>> queryHotTagTypes() {
        List<TagTypeRO> tags = socialTagStore.getHotTagTypes();
        return new ResultRO<>(tags);
    }
}