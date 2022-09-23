package com.socialuni.social.sdk.model.QO.dev;

import com.socialuni.social.sdk.dao.DO.dev.DevAccountDO;
import com.socialuni.social.sdk.dao.DO.dev.DevAccountProviderDO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SyncProdDevAccountQO {
    @NotNull
    private DevAccountDO devAccountDO;
    @NotNull
    private List<DevAccountProviderDO> devAccountProviders;

    public SyncProdDevAccountQO(DevAccountDO devAccountDO, List<DevAccountProviderDO> devAccountProviders) {
        this.devAccountDO = devAccountDO;
        this.devAccountProviders = devAccountProviders;
    }
}
