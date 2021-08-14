package com.socialuni.center.web.model.DO.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.center.web.repository.log.ErrorLogRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.ErrorLogUtils;
import social.web.sdk.utils.IpUtil;
import com.socialuni.social.sdk.utils.SocialHeaderUtil;
import com.socialuni.social.sdk.utils.common.JsonUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Entity
@Table(name = "error_log")
@Data@Component
//监控日志
public class ErrorLogDO {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogDO.class);

    private static ErrorLogRepository errorLogRepository;

    @Resource
    public void setErrorLogRepository(ErrorLogRepository errorLogRepository) {
        ErrorLogDO.errorLogRepository = errorLogRepository;
    }

    //必有
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;

    private Date createTime;

    //前台后台
    private String type;
    //重要级别 轻微，一般，重要，紧急
    private String level;
    //错误的uri
    private String uri;

    //出错的业务备注
    //错误信息
    @Column(columnDefinition = "text")
    private String errorMsg;

    private String userIp;

    //入参
    @Column(columnDefinition = "text")
    private String params;
    private Integer devId;

    public ErrorLogDO() {

    }

    public ErrorLogDO(Integer userId, String errorMsg) {
        this.userId = userId;
        this.devId = DevAccountUtils.getDevId();
        this.createTime = new Date();
        this.type = "后台";
        this.level = "一般";

        HttpServletRequest request = SocialHeaderUtil.getRequest();
        this.uri = request.getRequestURI();
        this.userIp = IpUtil.getIpAddr(request);

        this.errorMsg = errorMsg;
    }

    public ErrorLogDO(Integer userId, String errorMsg, Object data) {
        this(userId, errorMsg);
        try {
            this.params = JsonUtils.objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("记录错误日志，解析入参出错");
            ErrorLogUtils.save(new ErrorLogDO(userId, "记录错误日志，解析入参出错", errorMsg));
        }
    }

    public ErrorLogDO(Integer userId, String errorMsg, Object data, String level) {
        this(userId, errorMsg, data);
        this.level = level;
    }
}