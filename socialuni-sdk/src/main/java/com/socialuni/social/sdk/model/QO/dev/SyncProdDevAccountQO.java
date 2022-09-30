package com.socialuni.social.sdk.model.QO.dev;

import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.tance.entity.DevAccountProviderDO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SyncProdDevAccountQO {
    @NotNull
    private DevAccountModel devAccountModel;
    @NotNull
    private List<DevAccountProviderDO> devAccountProviders;

    public SyncProdDevAccountQO(DevAccountModel devAccountModel, List<DevAccountProviderDO> devAccountProviders) {
        this.devAccountModel = devAccountModel;
        this.devAccountProviders = devAccountProviders;
    }
}
