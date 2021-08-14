package com.socialuni.social.web.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ErrorCode;
import com.socialuni.social.constant.ErrorType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.base.SocialException;
import com.socialuni.social.sdk.model.OperateLogDO;
import com.socialuni.social.sdk.utils.RequestLogDOUtil;
import com.socialuni.social.sdk.utils.RequestLogUtil;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import com.socialuni.social.sdk.utils.common.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import social.web.sdk.utils.IpUtil;
import social.web.sdk.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class WebControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter,
                                  MediaType mediaType, Class clas, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        ServletServerHttpResponse sshrp = (ServletServerHttpResponse) serverHttpResponse;
        HttpServletResponse response = sshrp.getServletResponse();
        ResultRO responseVO;
        if (response.getStatus() != 200 && response.getStatus() != 404) {
            System.out.println("不应该触发这里");
//            ErrorLogUtils.save(new ErrorLogDO(UserUtils.getMineUserId(), "状态码非200，系统错误", result));
            responseVO = new ResultRO(ErrorCode.SYSTEM_ERROR, "系统异常");
        } else {
            if (result instanceof ResultRO) {
                responseVO = (ResultRO) result;
            } else {
                return result;
            }
        }
        if (responseVO.getErrorCode() > 0) {
            response.setStatus(responseVO.getErrorCode());
        }
        //返回修改后的值
        return responseVO;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class c) {
        //不拦截
        return true;
    }


    /**
     * 全局异常捕捉处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultRO<Void> systemExceptionHandler(Exception exception) {
        ResultRO<Void> resultRO = new ResultRO<>(500, "系统异常");
        log.info(exception.getClass().getName());
        log.info(exception.toString());
        String errorStr;
        try {
            errorStr = JsonUtils.objectMapper.writeValueAsString(exception);
        } catch (JsonProcessingException e) {
            errorStr = "解析异常出错";
            e.printStackTrace();
        }
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getErrorCode(), ErrorType.error, exception.toString(), errorStr);
        exception.printStackTrace();
        return resultRO;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultRO<Void> BindExceptionHandler(MethodArgumentNotValidException exception) {
        String msg = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ResultRO<Void> resultRO = new ResultRO<>(ErrorCode.PARAMS_ERROR, msg);
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getErrorCode(), ErrorType.error, msg, exception.getMessage());
        exception.printStackTrace();
        return resultRO;
    }

    @ExceptionHandler(value = SocialException.class)
    public ResultRO<Void> socialExceptionHandler(SocialException exception) {
        this.saveOperateLogDO(exception.getErrorMsg(), exception.getErrorCode(), exception.getErrorType(), exception.getInnerMsg(), null);
        exception.printStackTrace();
        ResultRO<Void> resultRO = new ResultRO<>(exception.getErrorCode(), exception.getErrorMsg());
        return resultRO;
    }

    private void saveOperateLogDO(String errorMsg, Integer errorCode, String errorType, String innerMsg, String innerMsgDetail) {
        OperateLogDO operateLogDO = RequestLogUtil.getAndRemove();
        if (operateLogDO == null) {
            operateLogDO = new OperateLogDO();
            HttpServletRequest request = RequestUtil.getRequest();
            UserDO user = SocialUserUtil.getMineUser();
            if (user != null) {
                operateLogDO.setUserId(user.getId());
            }
            String requestIp = IpUtil.getIpAddr(request);
            String uri = request.getRequestURI();
            String requestMethod = request.getMethod();
            operateLogDO.setIp(requestIp);
//            operateLogDO.setCreateTime(startTime);
            operateLogDO.setRequestMethod(requestMethod);
            operateLogDO.setUri(uri);
        }

        Date endDate = new Date();
        long spendTime = endDate.getTime() - operateLogDO.getCreateTime().getTime();
        operateLogDO.setSuccess(false);
        operateLogDO.setErrorCode(errorCode);
        operateLogDO.setErrorMsg(errorMsg);
        operateLogDO.setErrorType(errorType);
        operateLogDO.setInnerMsg(innerMsg);
        operateLogDO.setInnerMsgDetail(innerMsgDetail);
        operateLogDO.setEndTime(endDate);
        operateLogDO.setSpendTime(spendTime);
        RequestLogDOUtil.saveAsync(operateLogDO);

//        log.info("[requestId:{},{}],[{}({})][spendTimes:{}]", operateLogDO.getId(), operateLogDO.getErrorMsg(), operateLogDO.getRequestMethod(), operateLogDO.getUri(), spendTime);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResultRO<Void> notFound404ExceptionHandler(NoHandlerFoundException exception) {
        ResultRO<Void> resultRO = new ResultRO<>(404, "不存在的资源");
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getErrorCode(), ErrorType.error, resultRO.getErrorMsg(), exception.getMessage());
        return resultRO;
    }
}
