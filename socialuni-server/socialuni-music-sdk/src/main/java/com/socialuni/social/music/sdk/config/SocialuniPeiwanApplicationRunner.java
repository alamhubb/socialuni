package com.socialuni.social.peiwan.sdk.config;

import com.socialuni.social.peiwan.sdk.dao.rep.SocialuniPeiwanSkillRepository;
import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanSkillDO;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfigInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
public class SocialuniPeiwanApplicationRunner implements ApplicationRunner {


    @Resource
    SocialuniPeiwanSkillRepository socialuniPeiwanSkillRepository;

    @Override
    @Async
    public void run(ApplicationArguments args) {

        List<String> skillTags = Arrays.asList("吃饭", "打电竞", "看电影", "小酌");

        List<SocialuniPeiwanSkillDO> socialuniPeiwanSkillDOS = new ArrayList<>();

        for (String skillTag : skillTags) {
            SocialuniPeiwanSkillDO socialuniPeiwanSkillDO = socialuniPeiwanSkillRepository.findFirstBySkillName(skillTag);
            if (socialuniPeiwanSkillDO == null) {
                socialuniPeiwanSkillDO = new SocialuniPeiwanSkillDO();
                socialuniPeiwanSkillDO.setSkillName(skillTag);
                socialuniPeiwanSkillDOS.add(socialuniPeiwanSkillDO);
            }
        }

    }
}
