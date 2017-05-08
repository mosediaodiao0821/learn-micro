package com.dealmoon.ucenter.dbutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcContextHolder {
	static Logger logger = LoggerFactory.getLogger(JdbcContextHolder.class);

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setJdbcType(String jdbcType) {
		contextHolder.set(jdbcType);
	}

	public static String getJdbcType() {
		String s = (String) contextHolder.get();
		return s;
	}

	public static void clearJdbcType() {
		contextHolder.remove();
	}
}
