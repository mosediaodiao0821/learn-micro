package com.dealmoon.ucenter.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.dealmoon.libs.AbstractBase;
import com.dealmoon.libs.service.Mail;
import com.dealmoon.libs.service.Storage;
import com.fasterxml.jackson.databind.DeserializationFeature;

public abstract class AbstractController extends AbstractBase
{
	@Autowired
	protected Mail mailService;
	@Autowired
	protected Storage storageService;

	public AbstractController()
	{
		super();
		getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	protected  <T> T getCommandInfo(Object fromValue, Class<T> toValueType)
	{
		return getObjectMapper().convertValue(fromValue, toValueType);
	}
}