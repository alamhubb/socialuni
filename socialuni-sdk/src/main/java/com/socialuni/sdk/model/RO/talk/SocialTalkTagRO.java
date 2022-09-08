package com.socialuni.sdk.model.RO.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan
 * @date 2019-11-07 15:20
 */

@Data
@NoArgsConstructor
public class SocialTalkTagRO {
    public Integer id;
    public String name;

    public SocialTalkTagRO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
