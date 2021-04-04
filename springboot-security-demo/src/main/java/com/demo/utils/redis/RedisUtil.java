package com.demo.utils.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisUtil.java
 * @Description: redis数据库数据存储操作
 * @author: liuhoujie
 * @date: 2018年4月27日
 */
@Component
public class RedisUtil implements RedisBase {


//	@Resource
//	JedisPool jedisPool;
	@Resource
	RedisTemplate<String, String> redisTempate;

	@Override
	public void set(String key, String value) {
//		 Jedis jedis = jedisPool.getResource();
//		try {
//			jedis.set(key, value);
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		redisTempate.opsForValue().set(key, value);

	}

	@Override
	public void set(String key, String value, Integer seconds) {
//		Jedis jedis = jedisPool.getResource();
//		try {
//			jedis.set(key, value);
//			jedis.expire(key, seconds);
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		redisTempate.opsForValue().set(key, value, Long.valueOf(seconds),TimeUnit.SECONDS);
	}

	@Override
	public String get(String key) {
//		Jedis jedis = jedisPool.getResource();
//		try {
//			String value = jedis.get(key);
//			return value;
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		return redisTempate.opsForValue().get(key);

	}

	@Override
	public void del(String key) {
//		Jedis jedis = jedisPool.getResource();
//		try {
//			jedis.del(key);
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		
		redisTempate.delete(key);
	}

	@Override
	public void expire(String key, Integer seconds) {
//		Jedis jedis = jedisPool.getResource();
//		try {
//			jedis.expireAt(key, seconds);
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		
		redisTempate.expire(key,Long.valueOf(seconds), TimeUnit.SECONDS);
	}

	@Override
	public long incr(String key) {
//		Jedis jedis = jedisPool.getResource();
//		try {
//			Long value = jedis.incr(key);
//			return value;
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//		}
		return redisTempate.opsForValue().increment(key);
	}

}
