package com.ef.golf.util;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class RedisBaseDao {

	@Resource
	private JedisPool jedisPool;

	
	public boolean exist(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {//http://user-header.oss-cn-beijing.aliyuncs.com/1509074072722.jpg
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void expire(String key, int expiration) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, expiration);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void save(final String key, final String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void saveEx(final String key, final String value, final int existSeconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, existSeconds, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void delete(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String get(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Long hSet(final String key, final String field, final String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(key, field, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String hGet(final String key, final String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(key, field);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Map<String, String> hGetAll(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void hDelete(final String key, final String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hdel(key, field);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Set<String> hKeys(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hkeys(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	//可队列用
	public void lPush(final String key, final String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.lpush(key, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public void rPush(final String key, final String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.rpush(key, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	 //队列用
	public List<String> brPop(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.brpop(30, key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String lPop(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lpop(key);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	

	public List<String> lRange(final String key, int start, int end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, start, end);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {

				jedisPool.returnResource(jedis);
			}
		}
	}

	public List<String> lRange(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, 0, -1);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {

				jedisPool.returnResource(jedis);
			}
		}
	}

	public void lRem(final String key, final String value, final int count) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.lrem(key, count, value);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void sAdd(final String key, final String... members) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.sadd(key, members);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Set<String> sMembers(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public Boolean sisMember(final String key,final String member){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sismember(key, member);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Object eval(String script) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.eval(script);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Object eval(String script, List<String> keys, List<String> args) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.eval(script, keys, args);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Object evalSha(String luakey, List<String> keys, List<String> args) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.evalsha(luakey, keys, args);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String scriptLoad(String script) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.scriptLoad(script);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void sRem(final String key, final String... members) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.srem(key, members);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void flushAll() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.flushAll();
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public List<String> hvals(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hvals(key);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public boolean hExists(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hexists(key, field);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String Get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public String Set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(key, value);
		} catch (RuntimeException e) {
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public Long hLen(final String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hlen(key);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	/**
	 * 设置 list
	 * @param <T>
	 * @param key
	 * @param value
	 */
	public <T>  void setList(String key ,List<T> list){

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key.getBytes(), ObjectTranscoder.serialize(list));
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);

			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}


	/**
	 * 设置 list
	 * @param <T>
	 * @param key
	 * @param value
	 * @param existSeconds  有效期  单位是秒
	 */
	public <T>  void setList(String key ,List<T> list,int existSeconds){

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			jedis.setex(key.getBytes(),existSeconds, ObjectTranscoder.serialize(list));
			/*jedis.expire()*/
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}

	/**
	 * 获取list
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public   <T> List<T> getList(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			if(jedis== null || !jedis.exists(key.getBytes())){
				return null;
			}
			byte[] in = jedis.get(key.getBytes());
			List<T> list = (List<T>) ObjectTranscoder.deserialize(in);
			return list;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}


	}
	/**
	 * 设置 map
	 * @param <T>
	 * @param key
	 * @param value
	 * @param existSeconds  生存时间 单位是秒
	 */
	public  <T> void setMap(String key ,Map<String,T> map,int existSeconds){

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key.getBytes(),existSeconds,ObjectTranscoder.serialize(map));
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}

	/**
	 * 设置 map
	 * @param <T>
	 * @param key
	 * @param value
	 */
	public  <T> void setMap(String key ,Map<String,T> map){

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key.getBytes(),ObjectTranscoder.serialize(map));
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}
	/**
	 * 获取map
	 * @param <T>
	 * @param key
	 * @return list
	 */
	public <T> Map<String,T> getMap(String key){

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if(jedis == null || !jedis.exists(key.getBytes())){
				return null;
			}
			byte[] in = jedis.get(key.getBytes());
			Map<String,T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);
			return map;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (jedis != null) {
				jedis.disconnect();
				jedisPool.returnBrokenResource(jedis);
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

}
