package com.socialuni.sdk.model.QO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MockQO {
    private String mpType;

    public MockQO(String mpType) {
        this.mpType = mpType;
    }
}