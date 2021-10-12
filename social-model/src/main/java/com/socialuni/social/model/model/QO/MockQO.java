package com.socialuni.social.model.model.QO;

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