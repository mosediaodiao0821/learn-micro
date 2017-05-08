package com.dealmoon.ucenter.dbutil;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceAspectModel implements AfterReturningAdvice, MethodBeforeAdvice {
	static Logger logger = LoggerFactory.getLogger(DataSourceAspectModel.class);

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		JdbcContextHolder.clearJdbcType();
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		DataSource d = method.getAnnotation(DataSource.class);
		if (d != null) {
			JdbcContextHolder.setJdbcType(d.value());
		}
	}
}
