package com.socialuni.social.tance.dev.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @see <a href='https://blog.csdn.net/qq_38425719/article/details/110765161'>SpringBoot JPA 建立联合主键</a>
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/15 23:33
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfigPk  implements Serializable {
    private Integer devId;
    /**
     * key值
     */
    private String configKey;
}
