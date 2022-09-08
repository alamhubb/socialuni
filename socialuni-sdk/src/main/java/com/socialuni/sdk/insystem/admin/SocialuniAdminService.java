package com.socialuni.sdk.insystem.admin;

import com.socialuni.sdk.dao.repository.dev.DevAccountProviderRepository;
import com.socialuni.sdk.dao.repository.dev.DevAccountRepository;
import com.socialuni.sdk.logic.manage.SocialTagManage;
import com.socialuni.sdk.dao.repository.community.TagRepository;
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
    @Resource
    private SocialTagManage socialTagManage;
    @Resource
    private DevAccountProviderRepository devAccountProviderRepository;

    /*@Transactional
    public void syncProdDevAccountToDev(SyncProdDevAccountQO syncProdDevAccountQO) {
        //判断此开发者是否已经创建，没创建则创建，创建则修改
        DevAccountDO prodDevAccount = syncProdDevAccountQO.getDevAccountDO();
        DevAccountDO devAccountDO = devAccountRepository.findOneByDevNum(prodDevAccount.getDevNum());
        List<DevAccountProviderDO> devAccountProviders = syncProdDevAccountQO.getDevAccountProviders();
        //如果为空，则代表新建，则创建
        if (devAccountDO == null) {
            //新建dev，tag，providers
            //设置为null，因为id自动生成
            prodDevAccount.setId(null);
            devAccountDO = devAccountRepository.save(prodDevAccount);
            socialTagManage.createDevAccountTagDO(devAccountDO.getId(), devAccountDO.getAppName());
            if (devAccountProviders.size() < 1) {
                throw new SocialParamsException("必须存在开发者渠道信息");
            }
        } else {
            //使用本系统id
            prodDevAccount.setId(devAccountDO.getId());
            //更新dev
            devAccountDO = devAccountRepository.save(prodDevAccount);
            //名称不一致才修改name
            if (!devAccountDO.getAppName().equals(prodDevAccount.getAppName())) {
                TagDO devTag = tagRepository.findFirstByDevId(devAccountDO.getId());
                devTag.setName(prodDevAccount.getAppName());
                tagRepository.save(devTag);
            }
            //判断name有没有修改，有修改则修改tag
            //判断长度有没有大于1，有的话对应修改
        }
        //同步渠道信息
        for (DevAccountProviderDO prodDevAccountProvider : devAccountProviders) {
            DevAccountProviderDO devAccountProviderDO = devAccountProviderRepository.findOneByDevIdAndMpType(devAccountDO.getId(), prodDevAccountProvider.getMpType());
            //设置为null，自动生成
            if (devAccountProviderDO == null) {
                prodDevAccountProvider.setId(null);
            }
            //使用本系统id
            prodDevAccountProvider.setDevId(devAccountDO.getId());
            devAccountProviderRepository.save(prodDevAccountProvider);
        }
    }*/
}
