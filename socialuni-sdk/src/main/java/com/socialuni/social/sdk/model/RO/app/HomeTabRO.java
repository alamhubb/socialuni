package com.socialuni.social.sdk.model.RO.app;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class HomeTabRO {
    private String name;
//    private HomeTabCircleRO circle;


    public HomeTabRO(String name) {
        this.name = name;
    }
}
