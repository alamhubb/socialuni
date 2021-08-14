package com.socialuni.center.web.insystem.admin;

import com.socialuni.center.web.repository.DevAccountRepository;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.entity.model.DO.tag.TagDO;
import com.socialuni.social.sdk.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SocialuniAdminService {
    @Resource
    private DevAccountRepository devAccountRepository;
    @Resource
    private TagRepository tagRepository;

    public void syncProdDevAccountToDev(DevAccountDO devAccount) {
        devAccount.setId(null);
        DevAccountDO devAccountDO = devAccountRepository.save(devAccount);
        //创建话题，还要创建用户
        TagDO tagDO = new TagDO(1, devAccountDO.getDevNum().toString(), "开发者对应的话题");
        tagDO.setDevId(devAccountDO.getId());
        tagDO.setTagTypeId(32);
        tagDO = tagRepository.save(tagDO);
    }
}
