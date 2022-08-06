package com.socialuni.center.web.factory.DO;

import com.socialuni.center.web.model.DO.UnionIdDO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.center.web.utils.ObjectUtil;

import java.util.Date;

public class UnionIdDOFactory {
    public static UnionIdDO createUnionDO(String contentType, Integer contentId, Integer devId, Date curDate, Date beginTime, String uid, Integer userId) {
        //不存在已有的
        UnionIdDO unionIdDO = new UnionIdDO();
        //设置随机uuid
        unionIdDO.setStatus(CommonStatus.enable);
        unionIdDO.setUnionId(uid);
        unionIdDO.setContentType(contentType);
        unionIdDO.setContentId(contentId);
        unionIdDO.setDevId(devId);
        unionIdDO.setUserId(userId);

        unionIdDO.setCreateTime(curDate);
        unionIdDO.setUpdateTime(curDate);
        unionIdDO.setBeginTime(beginTime);
        //userId和devId有一个为空，则有过期时间
        if (ObjectUtil.hasEmpty(userId, devId)) {
            unionIdDO.setValidTime(DateTimeType.halfHour + DateTimeType.minute * 10);
            unionIdDO.setExpiredTime(new Date(beginTime.getTime() + unionIdDO.getValidTime()));
        }
        return unionIdDO;
    }
}
