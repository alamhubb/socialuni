package com.socialuni.sdk.logic.domain.tag;

import com.socialuni.sdk.logic.check.SocialuniUserCheck;
import com.socialuni.sdk.logic.factory.community.SocialTagROFactory;
import com.socialuni.sdk.logic.manage.SocialTagManage;
import com.socialuni.sdk.dao.DO.tag.TagDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.repository.community.TagRepository;
import com.socialuni.sdk.logic.service.content.SocialuniContentCheckUtil;
import com.socialuni.social.web.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.model.QO.community.tag.TagAddQO;
import com.socialuni.sdk.model.RO.community.tag.TagRO;
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
    private SocialTagManage socialTagManage;

    public TagRO addTag(SocialuniUserDO mineUser, TagAddQO tagAddVO) {
        //校验用户
        SocialuniUserCheck.checkUserBindPhoneNumAndStatusNoEnable(mineUser);
        /*if (SocialAppEnv.getIsDevProdEnv()) {
            throw new SocialBusinessException("demo演示环境不支持创建tag，防止tag与生产环境不一致");
        }*/
        String tagName = tagAddVO.getTagName();
        if (tagName.length() > 6) {
            throw new SocialBusinessException("话题最多支持六个字");
        }
        SocialuniContentCheckUtil.checkUserInputLongTextContent(tagName);
        //校验内容是否违规

        TagDO dbTag = tagRepository.findFirstByName(tagName);
        //toDO 这里有坑，就是没有查询标签状态，如果标签已经禁用，这里也可以直接用了
        if (dbTag != null) {
            throw new SocialBusinessException("标签已经存在，请直接使用");
        }
        String description = tagAddVO.getDescription();
        SocialuniContentCheckUtil.checkUserInputLongTextContent(description);
        TagDO tagDO = socialTagManage.createTagDO(tagAddVO, mineUser.getUnionId());
        TagRO tagRO = SocialTagROFactory.getTagRO(tagDO);
        return tagRO;
    }

}
