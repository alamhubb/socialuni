package com.socialuni.social.model.model.QO;

import com.socialuni.social.entity.model.DO.log.FrontErrorLogDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import lombok.Data;

import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:48
 */
//记录前端错误日志，不该出现的错误
@Data
public class FrontErrorLogVO {
    //出错的url
    private String uri;

    //出错的业务备注
    private String detail;

    //传入了什么参数导致报错
    private String params;

    //错误信息
    private String errorMsg;
    private String platform;
    private String provider;
    private String appVersion;


}
