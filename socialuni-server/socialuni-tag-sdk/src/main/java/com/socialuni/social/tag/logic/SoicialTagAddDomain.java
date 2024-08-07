package com.socialuni.social.tag.logic;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.tag.dao.DO.SocialuniTagDO;
import com.socialuni.social.tag.dao.repository.TagRepository;
import com.socialuni.social.user.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.social.tag.factory.SocialTagROFactory;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.QO.tag.TagAddQO;
import com.socialuni.social.tag.model.TagRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-03-19 20:05
 */
@Service
public class SoicialTagAddDomain {
    @Resource
    private TagRepository tagApi;
    @Resource
    private SocialTagManage socialTagManage;

    public TagRO addTag(SocialuniUserDo mineUser, TagAddQO tagAddVO) {
        //校验用户
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusEnable();
        /*if (SocialAppEnv.getIsDevProdEnv()) {
            throw new SocialBusinessException("demo演示环境不支持创建tag，防止tag与生产环境不一致");
        }*/
        String tagName = tagAddVO.getTagName();
        if (tagName.length() > 6) {
            throw new SocialBusinessException("话题最多支持六个字");
        }
        SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(tagName);
        //校验内容是否违规

        SocialuniTagDO dbTag = tagApi.findFirstByName(tagName);
        //toDO 这里有坑，就是没有查询标签状态，如果标签已经禁用，这里也可以直接用了
        if (dbTag != null) {
            throw new SocialBusinessException("标签已经存在，请直接使用");
        }
        String description = tagAddVO.getDescription();
        SocialuniTextContentUtil.checkTextHasUnderageAndContactAndViolateWords(description);
        SocialuniTagDO TagDO = socialTagManage.createTagDO(tagAddVO, mineUser.getUnionId());
        TagRO tagRO = SocialTagROFactory.getTagRO(TagDO);
        return tagRO;
    }

}
