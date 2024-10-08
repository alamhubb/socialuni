package com.socialuni.social.community.sdk.logic.manage.talk;

import com.socialuni.social.user.sdk.dao.DO.SocialuniDistrictDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.community.sdk.repository.TalkAdultImgAuditRepository;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDORedis;
import com.socialuni.social.community.sdk.logic.factory.TalkDOFactory;
import com.socialuni.social.content.utils.SocialuniTextContentUtil;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.content.utils.SocialuniImgContentUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class SocialTalkCreateManage {
    @Resource
    SocialuniTalkDORedis talkRedis;
    @Resource
    TalkAdultImgAuditRepository talkAdultImgAuditRepository;

    public SocialuniTalkDO createTalkDO(SocialuniUserDo user, SocialuniTalkPostQO socialTalkPostQO, SocialuniDistrictDO district) {
        SocialuniTalkDO talkDO = TalkDOFactory.newTalkDO(user, socialTalkPostQO, district);

        //img相关放在外面，因为有 注入，省事而已
        List<SocialuniImgAddQO> imgs = socialTalkPostQO.getImgs();
        for (SocialuniImgAddQO img : imgs) {
            SocialuniTextContentUtil.setHasUnderageAndContactInfoByContentText(img.getContent(), talkDO);
            if (img.getHasQrCode()) {
                talkDO.setHasQrCode(true);
            }
            //只有不存在人像，才校验设置，已存在则跳过
            if (!talkDO.getHasPeopleImg()) {
                boolean hasPeople = SocialuniImgContentUtil.hasPeopleImg(img.getSrc());
                if (hasPeople) {
                    talkDO.setHasPeopleImg(true);
                    Long talkUnionId = talkDO.getUnionId();
                    Long userId = talkDO.getUserId();
                    //如果包含人像，则需要生成一条待审核记录
                    CompletableFuture.runAsync(() -> {
                        SocialuniTalkHasUnderageImgAuditDO socialTalkAdultAuditDO = new SocialuniTalkHasUnderageImgAuditDO(talkUnionId, userId);
                        talkAdultImgAuditRepository.save(socialTalkAdultAuditDO);
                    });
                }
            }
        }

        //保存说说
        talkDO = talkRedis.save(talkDO);
        return talkDO;
    }
}
