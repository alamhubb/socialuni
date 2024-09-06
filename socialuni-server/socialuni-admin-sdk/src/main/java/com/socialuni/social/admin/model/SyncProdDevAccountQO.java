package com.socialuni.social.admin.model;

import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.model.DevAccountProviderModler;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SyncProdDevAccountQO {
    @NotNull
    private DevAccountDo devAccountDo;
    @NotNull
    private List<DevAccountProviderModler> devAccountProviders;

    public SyncProdDevAccountQO(DevAccountDo devAccountDo, List<DevAccountProviderModler> devAccountProviders) {
        this.devAccountDo = devAccountDo;
        this.devAccountProviders = devAccountProviders;
    }
}
