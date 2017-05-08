package com.dealmoon.ucenter.dbutil;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	org.slf4j.Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(DynamicDataSource.class);
	@Override
	protected Object determineCurrentLookupKey() {
		String j = JdbcContextHolder.getJdbcType();
		return j;
	}

	@Override
	public Logger getParentLogger() {
		return null;
	}
}
