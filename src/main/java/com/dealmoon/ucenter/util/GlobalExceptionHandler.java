package com.dealmoon.ucenter.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealmoon.ucenter.models.SdkResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
    public Map<String,Object> handleException(HttpServletRequest req, Exception e) {
        //打印异常信息：
		logger.error("错误日志：" + req.getRequestURI(),e);
		Map<String,Object> error = new HashMap<String,Object>();
		SdkResult result = new SdkResult(req.getParameter("lang"));
		result.setCode(26);
		error.put("result", result);
		return error;
    }

}
