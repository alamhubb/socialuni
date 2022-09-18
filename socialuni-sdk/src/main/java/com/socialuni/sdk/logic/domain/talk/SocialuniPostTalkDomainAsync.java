package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.dao.DO.talk.*;
import com.socialuni.sdk.dao.repository.community.*;
import com.socialuni.sdk.logic.domain.report.ReportDomain;
import com.socialuni.sdk.logic.factory.TalkImgDOFactory;
import com.socialuni.sdk.logic.service.content.SocialuniContentCheckUtil;
import com.socialuni.sdk.model.QO.community.talk.SocialuniTalkPostQO;
import com.socialuni.sdk.utils.ImgContentUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class SocialuniPostTalkDomainAsync {
    @Resource
    TalkImgRepository talkImgRepository;

    @Resource
    TalkAdultImgAuditRepository talkAdultAuditRepository;

    @Resource
    ReportDomain reportDomain;

//    @Async
    public void saveTalkImgs(SocialuniTalkPostQO socialTalkPostQO, SocialTalkDO talkDO) {
        List<SocialTalkImgDO> imgDOS = TalkImgDOFactory.newTalkImgDOS(socialTalkPostQO.getImgs());

        //用户是否已经认证
        Boolean userIdentityAuth = SocialuniUserUtil.getUserIsIdentityAuth(talkDO.getUserId());

        for (SocialTalkImgDO talkImgDO : imgDOS) {
            talkImgDO.setTalkId(talkDO.getUnionId());
            talkImgDO.setUserId(talkDO.getUserId());
            //包含未成年内容
            boolean hasUn18Content = SocialuniContentCheckUtil.hasUn18ContentThrowError(talkImgDO.getContent());
            talkImgDO.setHasUnderageContent(hasUn18Content);
            //是否成年认证通过
            talkImgDO.setAdultAuth(false);
            //是否包含人物图片
            boolean hasPeople = ImgContentUtil.hasPeopleImg(talkImgDO.getSrc());
            //如果用户包含人物头像,且用户未认证,
            if (hasPeople) {
                talkImgDO.setHasPeopleImg(true);
                // todo && !imgDO.getAdultAuth() 目前认证不严格，所以暂时认证的也需要审核
//                if (!imgDO.getAdultAuth()) {
                //则添加一条待审核的动态，qq平台只查询审核通过的动态
//                }
                CompletableFuture.runAsync(()->{
                    SocialTalkImgAdultAuditDO socialTalkAdultAuditDO = new SocialTalkImgAdultAuditDO(talkImgDO.getUnionId());
                    talkAdultAuditRepository.save(socialTalkAdultAuditDO);
                });
            }
            reportDomain.checkKeywordsCreateReport(talkImgDO);
        }
        imgDOS = talkImgRepository.saveAll(imgDOS);
        talkDO.setImgs(imgDOS);
    }
}
