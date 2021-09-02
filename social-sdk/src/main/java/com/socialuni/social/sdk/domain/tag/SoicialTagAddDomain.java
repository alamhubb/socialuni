package com.socialuni.social.sdk.domain.tag;

import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.sdk.factory.SocialTagROFactory;
import com.socialuni.social.sdk.manage.SocialTagManage;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.platform.tencent.TencentCloud;
import com.socialuni.social.sdk.platform.weixin.HttpResult;
import com.socialuni.social.sdk.repository.TagRepository;
import com.socialuni.social.sdk.service.comment.IllegalWordService;
import com.socialuni.social.sdk.utils.WxUtil;
import com.socialuni.social.model.model.QO.community.tag.TagAddQO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class SoicialTagAddDomain {
    @Resource
    private TagRepository tagRepository;
    @Resource
    private IllegalWordService illegalWordService;
    @Resource
    private SocialTagManage socialTagManage;

    public TagRO addTag(UserDO mineUser, TagAddQO tagAddVO) {
        String tagName = tagAddVO.getTagName();
        if (tagName.length() > 4) {
            throw new SocialBusinessException("话题最多支持四个字");
        }
        illegalWordService.checkHasIllegals(tagName);
        //校验内容是否违规

        TagDO optionalTagDO = tagRepository.findFirstByName(tagName);
        //toDO 这里有坑，就是没有查询标签状态，如果标签已经禁用，这里也可以直接用了
       /* if (optionalTagDO.isPresent()) {
            return new ResultRO<>(ErrorCode.CUSTOM_ERROR, "标签已经存在，请直接使用", new TagVO(optionalTagDO.get()));
        }*/
        if (TencentCloud.textIsViolation(tagName)) {
            throw new SocialBusinessException("标签名称违规");
        }

        String description = tagAddVO.getDescription();
        if (StringUtils.isNotEmpty(description)) {
            HttpResult wxDesResult = WxUtil.checkContentWxSec(description);
            if (wxDesResult.hasError()) {
                throw new SocialBusinessException("标签描述包含违规内容，禁止发布，请修改后重试");
            }
        }
        TagDO tagDO = socialTagManage.createTagDO(tagAddVO, mineUser.getId());
        TagRO tagRO = SocialTagROFactory.getTagRO(tagDO);
        return tagRO;
    }

}
