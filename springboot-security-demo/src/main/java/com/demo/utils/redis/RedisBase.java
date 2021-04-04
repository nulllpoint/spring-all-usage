package com.demo.utils.redis;

public interface RedisBase {
	/**
	 * 保存
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * 保存数据的同时，设置数据的有效时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void set(String key, String value, Integer seconds);

	/**
	 * 查询
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 删除
	 * 
	 * @param key
	 */
	public void del(String key);

	/**
	 * 根据key设置数据的有效时间
	 * 
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, Integer seconds);

	/**
	 * incr，每次执行加一
	 * 
	 * @param key
	 * @return
	 */
	public long incr(String key);

}
