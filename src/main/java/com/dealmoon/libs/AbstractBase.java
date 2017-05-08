package com.dealmoon.libs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractBase {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper;
    
    protected ObjectMapper getObjectMapper(){
		if(mapper==null){
			mapper = new ObjectMapper(); // create once, reuse
			mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			mapper.setSerializationInclusion(Include.NON_NULL);
		}
		return mapper;
	}
}
