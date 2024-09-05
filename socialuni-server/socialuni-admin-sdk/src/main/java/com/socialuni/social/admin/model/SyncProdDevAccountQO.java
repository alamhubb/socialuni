package com.socialuni.social.admin.model;

import com.socialuni.social.tance.dev.model.DevAccountModel;
import com.socialuni.social.tance.dev.model.DevAccountProviderModler;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SyncProdDevAccountQO {
    @NotNull
    private DevAccountModel devAccountModel;
    @NotNull
    private List<DevAccountProviderModler> devAccountProviders;

    public SyncProdDevAccountQO(DevAccountModel devAccountModel, List<DevAccountProviderModler> devAccountProviders) {
        this.devAccountModel = devAccountModel;
        this.devAccountProviders = devAccountProviders;
    }
}
