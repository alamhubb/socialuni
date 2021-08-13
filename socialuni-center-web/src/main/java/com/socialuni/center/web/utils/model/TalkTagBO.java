/*
package com.socialuni.center.web.utils.model;

import com.socialuni.social.model.RO.talk.SocialTalkTagRO;
import com.socialuni.web.model.talk.TagDO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 *//*


@Data
public class TalkTagBO {
    public Integer id;
    public String name;

    public TalkTagBO() {
    }

    public TalkTagBO(TagDO tagDO) {
        this.id = tagDO.getId();
        this.name = StringUtils.substring(tagDO.getName(), 0, 4);
    }

    public static List<TalkTagBO> tagDOToVOS(List<TagDO> DOs) {
        return DOs
                .stream()
                .map(
                        TalkTagBO::new)
                .collect(Collectors.toList());
    }

    public SocialTalkTagRO toVO() {
        SocialTalkTagRO talkTagVO = new SocialTalkTagRO();
        talkTagVO.setId(this.id);
        talkTagVO.setName(this.name);
        return talkTagVO;
    }

    public static List<SocialTalkTagRO> toVOs(List<TalkTagBO> tagVOS) {
        return tagVOS.stream().map(TalkTagBO::toVO).collect(Collectors.toList());
    }
}
*/
