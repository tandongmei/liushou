package com.ls.handler;

import com.ls.common.RestfulResponse;
import com.ls.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author:yuhang
 * @Date:2018/3/4
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public RestfulResponse serviceException(ServiceException e){
        RestfulResponse restfulResponse=new RestfulResponse();
        restfulResponse.setCode(e.getCode());
        restfulResponse.setMsg(e.getMessage());
        return restfulResponse;
    }
}
