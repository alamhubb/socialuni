package com.socialuni.social.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.event.WebControllerExceptionEvent;
import com.socialuni.social.common.constant.ErrorCode;
import com.socialuni.social.common.constant.ErrorType;
import com.socialuni.social.common.constant.ErrorMsg;
import com.socialuni.social.common.exception.base.SocialException;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.common.utils.JsonUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import javax.servlet.http.HttpServletResponse;
import java.nio.ByteBuffer;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class SocialWebControllerAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter,
                                  MediaType mediaType, Class clas, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("触发了发送1111111111t");
        ServletServerHttpResponse sshrp = (ServletServerHttpResponse) serverHttpResponse;
        HttpServletResponse response = sshrp.getServletResponse();
        // 兼容没有返回体的。
        if (response.getStatus() == 200 && result instanceof ResultRO) {
            Integer resCode = ((ResultRO<?>) result).getCode();
            if (resCode > 0) {
                response.setStatus(resCode);
            }
            return result;
        }else{
            return ResultRO.success(result);
        }
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
        log.info("触发了发送122222t");
        ResultRO<Void> resultRO = new ResultRO<>(500, ErrorMsg.systemErrorMsg);
        String errorStr = exception.toString();
        if (StringUtils.isEmpty(errorStr)) {
            try {
                errorStr = JsonUtil.objectMapper.writeValueAsString(exception);
            } catch (JsonProcessingException e) {
                errorStr = "解析异常出错";
                e.printStackTrace();
            }
        }
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getCode(), ErrorType.error, exception.toString(), errorStr);
        exception.printStackTrace();
        return resultRO;
    }

    @ExceptionHandler(value = FeignException.class)
    public ResultRO<Void> feignExceptionHandler(FeignException feignException) {
        log.info("触发了发送1233333333");
        Optional<ByteBuffer> responseOpt = feignException.responseBody();
        feignException.printStackTrace();
        if (responseOpt.isPresent()) {
            ByteBuffer byteBuffer = responseOpt.get();
            ResultRO<Void> resultRO = JsonUtil.parse(byteBuffer, new ResultRO<>());
            this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getCode(), ErrorType.error, resultRO.getErrorMsg(), feignException.toString());
            return resultRO;
        } else {
            String errorStr;
            try {
                errorStr = JsonUtil.objectMapper.writeValueAsString(feignException);
            } catch (JsonProcessingException e) {
                errorStr = "解析异常出错";
                e.printStackTrace();
            }
            ResultRO<Void> resultRO = new ResultRO<>(500, ErrorMsg.systemErrorMsg);
            this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getCode(), ErrorType.error, feignException.toString(), errorStr);
            return resultRO;
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultRO<Void> BindExceptionHandler(MethodArgumentNotValidException exception) {
        log.info("触发了发送15555555553");
        String msg = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ResultRO<Void> resultRO = new ResultRO<>(ErrorCode.PARAMS_ERROR, msg);
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getCode(), ErrorType.error, msg, exception.getMessage());
        exception.printStackTrace();
        return resultRO;
    }

    @ExceptionHandler(value = SocialException.class)
    public ResultRO<Void> socialExceptionHandler(SocialException exception) {
        log.info("触发了发送166666663");
        this.saveOperateLogDO(exception.getErrorMsg(), exception.getErrorCode(), exception.getErrorType(), exception.getInnerMsg(), null);
        exception.printStackTrace();
        ResultRO<Void> resultRO = new ResultRO<>(exception.getErrorCode(), exception.getErrorMsg());
        return resultRO;
    }

    private void saveOperateLogDO(String errorMsg, Integer errorCode, String errorType, String innerMsg, String innerMsgDetail) {
        log.info("触发了发送event");
        WebControllerExceptionEvent webControllerExceptionEvent =new WebControllerExceptionEvent();
        webControllerExceptionEvent.setErrorMsg(errorMsg);
        webControllerExceptionEvent.setErrorCode(errorCode);
        webControllerExceptionEvent.setErrorType(errorType);
        webControllerExceptionEvent.setInnerMsg(innerMsg);
        webControllerExceptionEvent.setInnerMsgDetail(innerMsgDetail);
        // 发布事件解耦。
        this.applicationEventPublisher.publishEvent(webControllerExceptionEvent);
   }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResultRO<Void> notFound404ExceptionHandler(NoHandlerFoundException exception) {
        log.info("触发了发送666666666666t");
        ResultRO<Void> resultRO = new ResultRO<>(404, "不存在的资源");
        this.saveOperateLogDO(resultRO.getErrorMsg(), resultRO.getCode(), ErrorType.error, resultRO.getErrorMsg(), exception.getMessage());
        return resultRO;
    }
}
