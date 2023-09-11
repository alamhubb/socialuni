package com.socialuni.social.music.sdk.model.DO;

import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import com.socialuni.social.tance.model.DO.AppConfigPk;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-12-22 18:46
 */
@Data
@Entity
/**
 * 全局配置表
 * @deprecated 表设计冗余。
 */
@Table(name = "s_music_app_config",
        indexes = {
                @Index(columnList = "devId"),
                @Index(columnList = "status"),
                @Index(columnList = "configKey"),
                @Index(columnList = "createTime")
        }
)
@IdClass(value = AppConfigPk.class)
public class SocialuniMusicAppConfigDO {
    @Id
    private Integer devId;
    /**
     * key值
     */
    @Id
    private String configKey;
    private String value;
    private String label;
    private int status;
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createTime;

    public SocialuniMusicAppConfigDO() {
        this.status = 1;
    }
}
