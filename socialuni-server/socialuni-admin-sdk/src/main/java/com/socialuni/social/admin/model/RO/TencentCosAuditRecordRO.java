package com.socialuni.social.admin.model.RO;

import com.socialuni.social.admin.constant.AdminAuditResultType;
import com.socialuni.social.admin.factory.ReportContentROFactory;
import com.socialuni.social.admin.model.ReportContentVO;
import com.socialuni.social.common.api.constant.SocialuniContentType;
import com.socialuni.social.sdk.dao.DO.TencentCosAuditRecordDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.Data;

@Data
public class TencentCosAuditRecordRO {
    private Boolean checked;
    private Integer id;
    //唯一ID标识。
    private String traceId;
    //若您设置了自动冻结，该字段表示视频的冻结状态。0：未冻结，1：已被冻结。
    private Integer forbiddenStatus;
    //审核场景为涉黄的审核结果信息
//    private TencentCosCallbackDataPornInfo porn_info;
    //是否命中该审核分类，0表示未命中，1表示命中，2表示疑似。
    private Integer hitFlag;
    //该字段表示审核结果命中审核信息的置信度，取值范围：0（置信度最低）-100（置信度最高 ），
    // 越高代表该内容越有可能属于当前返回审核信息。通常[0,60]分可以认为图片属于正常，
    // [61,90]分表示图片疑似敏感，[91,100]分表示图片为敏感的。
    //例如：色情 99，则表明该内容非常有可能属于色情内容。
    private Integer score;
    //该字段表示本次判定的审核结果，您可以根据该结果，进行后续的操作；建议您按照业务所需，对不同的审核结果进行相应处理。
    // 有效值：0（审核正常），1 （判定为违规敏感文件），2（疑似敏感，建议人工复核）。
    private Integer result;
    //被审核图片的完整 URL 链接
    private String url;
    //加入一列作为外键
    private ReportContentVO talk;
    //用户昵称
    private String nickname;
    private String avatar;
    private String violateType;

    public TencentCosAuditRecordRO(TencentCosAuditRecordDO recordDO) {
        this.checked = true;
        this.id = recordDO.getId();
//        this.traceId = recordDO.getTraceId();
//        this.forbiddenStatus = recordDO.getForbiddenStatus();
        this.hitFlag = recordDO.getHitFlag();
        this.score = recordDO.getScore();
        this.result = recordDO.getResult();
        this.url = recordDO.getUrl();
        this.violateType = AdminAuditResultType.noViolation;
        if (recordDO.getContentType().equals(SocialuniContentType.talk)) {
            this.talk = ReportContentROFactory.getReportContentVO(SocialuniContentType.talk, recordDO.getImgParentContentId());
        }
        SocialuniUserDo userDO = SocialuniUserUtil.getAllowNull(recordDO.getUserId());
        if (userDO != null) {
            this.setAvatar(userDO.getAvatar());
            this.setNickname(userDO.getNickname());
        }
    }
}
