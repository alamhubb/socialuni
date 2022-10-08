package com.socialuni.social.sdk.logic.manage.talk;

import com.socialuni.social.sdk.dao.DO.DistrictDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.DO.community.talk.SocialuniTalkHasUnderageImgAuditDO;
import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.sdk.dao.repository.community.TalkAdultImgAuditRepository;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.sdk.logic.factory.TalkDOFactory;
import com.socialuni.social.sdk.logic.service.content.SocialuniTextContentUtil;
import com.socialuni.social.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.social.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.social.sdk.utils.SocialuniImgContentUtil;
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

    public SocialuniTalkDO createTalkDO(SocialuniUserDO user, SocialuniTalkPostQO socialTalkPostQO, DistrictDO district) {
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
                    Integer talkUnionId = talkDO.getUnionId();
                    Integer userId = talkDO.getUserId();
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
