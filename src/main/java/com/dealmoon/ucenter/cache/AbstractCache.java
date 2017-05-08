package com.dealmoon.ucenter.cache;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AbstractCache {
	
	@Resource(name="redisWriteTemplate")
	private StringRedisTemplate redisWriteTemplate;
	
	@Resource(name="redisReadTemplate")
	private StringRedisTemplate redisReadTemplate;
	
	@Resource
	private ObjectMapper objectMapper;
	
	private Logger logger = LoggerFactory.getLogger(AbstractCache.class);
	
	public <T> T get(String key, Class<T> classType){
		String jsonValue = redisReadTemplate.opsForValue().get(key);
		if(StringUtils.isEmpty(jsonValue)){
			return null;
		}
		try {
			return objectMapper.readValue(jsonValue, classType);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public <T> T get(String key, TypeReference<?> toValueTypeRef) {
		String jsonValue = redisReadTemplate.opsForValue().get(key);
		if(StringUtils.isEmpty(jsonValue)){
			return null;
		}
		try {
			return objectMapper.readValue(jsonValue, toValueTypeRef);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public void put(String key, Object o, long timeout){
		try {
			redisWriteTemplate.opsForValue().set(key, objectMapper.writeValueAsString(o), timeout, TimeUnit.SECONDS);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void delete(String key){
		redisWriteTemplate.delete(key);
	}
	
	public void deleteBatch(List<String> keys){
		redisWriteTemplate.delete(keys);
	}
	
	public <T> T hget(String key, String field, TypeReference<?> toValueTypeRef){
		Object value = redisReadTemplate.opsForHash().get(key, field);
		if(value != null){
			try {
				return objectMapper.readValue(value.toString(), toValueTypeRef);
			} catch (JsonParseException e) {
				logger.error(e.getMessage(), e);
			} catch (JsonMappingException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	public <T> T hget(String key, String field, Class<T> classType){
		Object value = redisReadTemplate.opsForHash().get(key, field);
		if(value != null){
			try {
				return objectMapper.readValue(value.toString(), classType);
			} catch (JsonParseException e) {
				logger.error(e.getMessage(), e);
			} catch (JsonMappingException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	public void hdel(String key, String field){
		redisWriteTemplate.opsForHash().delete(key, field);
	}
	
	public void hset(String key, String field, Object o, long timeout){
		try {
			redisWriteTemplate.opsForHash().put(key, field, objectMapper.writeValueAsString(o));
			redisWriteTemplate.expire(key, timeout, TimeUnit.SECONDS);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void incr(String key){
		redisWriteTemplate.opsForValue().increment(key, 1);
	}
	
	public void incrBy(String key, long increment){
		redisWriteTemplate.opsForValue().increment(key, increment);
	}
	
	public boolean exists(String key){
		return redisReadTemplate.hasKey(key);
	}
	
	public void expire(String key, long timeout){
		redisWriteTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	public Integer getKeyPosition(Integer id){
		return id / 100;
	}
}
