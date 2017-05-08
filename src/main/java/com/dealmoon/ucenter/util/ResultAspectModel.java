package com.dealmoon.ucenter.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.dealmoon.libs.AbstractBase;

public class ResultAspectModel extends AbstractBase implements AfterReturningAdvice, MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		for (int i = 0; i < args.length; i++) {
			try {
				if(!(method.getParameterTypes()[i].newInstance() instanceof Map)){
					logger.info("[" + method.getName() +"] req_body ==> " + getObjectMapper().writeValueAsString(args[i]));
				}
			} catch (Exception e) {
				logger.info("[" + method.getName() +"] req_params ==> " + getObjectMapper().writeValueAsString(args[i]));
			}
		}
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object target) throws Throwable {
		logger.info("[" + method.getName() +"] res_body ==> " + getObjectMapper().writeValueAsString(returnValue));
	}
	
}
