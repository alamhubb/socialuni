package model.DO;

import com.socialuni.social.common.api.entity.SocialuniBaseDO;
import com.socialuni.social.common.api.entity.SocialuniContentBaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "s_peiwan_info_img",
        //查询条件索引
        indexes = {
                //关联需要键索引，索引列不能为空
                @Index(columnList = "peiwanId"),
        }
)
@Data
public class SocialuniPeiwanInfoImgDO extends SocialuniContentBaseDO {

    private Integer peiwanId;
    private String src;
}
