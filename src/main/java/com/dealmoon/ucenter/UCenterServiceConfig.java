package com.dealmoon.ucenter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableCaching
@Configuration
public class UCenterServiceConfig {
	
	@Bean(name="redisWriteTemplate")
    public StringRedisTemplate redisWriteTemplate(
            @Value("${redis.write.host}") String hostName,  
            @Value("${redis.write.port}") int port,  
            @Value("${redis.write.maxidleclient}") int maxIdle,  
            @Value("${redis.write.maxtotalclient}") int maxTotal,  
            @Value("${redis.write.maxwait}") long maxWaitMillis) {
		StringRedisTemplate temple = new StringRedisTemplate();
		temple.setConnectionFactory(connectionFactory(hostName, port, "", maxIdle, maxTotal, 0, maxWaitMillis, true));
		temple.setKeySerializer(new StringRedisSerializer());
		temple.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		return temple;
    }
	
	@Bean(name="redisReadTemplate")
    public StringRedisTemplate redisReadTemplate(
    		@Value("${redis.read.host}") String hostName,  
            @Value("${redis.read.port}") int port,  
            @Value("${redis.read.maxidleclient}") int maxIdle,  
            @Value("${redis.read.maxtotalclient}") int maxTotal,  
            @Value("${redis.read.maxwait}") long maxWaitMillis) {
		StringRedisTemplate temple = new StringRedisTemplate();
		temple.setConnectionFactory(connectionFactory(hostName, port, "", maxIdle, maxTotal, 0, maxWaitMillis, true));
		temple.setKeySerializer(new StringRedisSerializer());
		temple.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		return temple;
    }
  
    public RedisConnectionFactory connectionFactory(String hostName, int port,  
            String password, int maxIdle, int maxTotal, int index,  
            long maxWaitMillis, boolean testOnBorrow) {  
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		jedis.setHostName(hostName);
		jedis.setPort(port);
		if (!StringUtils.isEmpty(password)) {
			jedis.setPassword(password);
		}
		if (index != 0) {
			jedis.setDatabase(index);
		}
		jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
		// 初始化连接pool
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		return factory;  
    }
  
	public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
		JedisPoolConfig poolCofig = new JedisPoolConfig();
		poolCofig.setMaxIdle(maxIdle);
		poolCofig.setMaxTotal(maxTotal);
		poolCofig.setMaxWaitMillis(maxWaitMillis);
		poolCofig.setTestOnBorrow(testOnBorrow);
		return poolCofig;
	} 
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper;
	}
}
