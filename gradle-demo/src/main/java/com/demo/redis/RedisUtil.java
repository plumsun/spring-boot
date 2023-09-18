package com.demo.redis;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Redis util.
 *
 * @author LiHaoHan
 * @date 2023 /2/28
 */
@Component
public class RedisUtil {

    private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();

    private static final String LOCK_SCRIPT = "if redis.call('setNx',KEYS[1],ARGV[1]) " +
            "then if redis.call('get',KEYS[1])==ARGV[1] " +
            "then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

    private static final String UNLOCK_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final Long SUCCESS_CODE = 1L;

    @Resource
    private JedisCluster jedisCluster;

    @Value("${spring.application.name}")
    private String applicationName;


    private String addPrefix(String key){
        return this.applicationName + ":" + key;
    }

    /**
     * Sets val.
     *
     * @param key   the key
     * @param value the value
     */
//-------------------------------------------String----------------------------------------------------
    public void setVal(String key, String value) {
        jedisCluster.set(addPrefix(key), value);
    }

    /**
     * Sets ex.
     *
     * @param key    the key
     * @param value  the value
     * @param seconds the seconds
     */
    public void setEx(String key, String value, int seconds) {
        jedisCluster.setex(addPrefix(key), seconds, value);
    }

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    public String get(String key) {
        return jedisCluster.get(addPrefix(key));
    }

    /**
     * Remove.
     *
     * @param key the key
     */
    public void remove(String key) {
        jedisCluster.del(addPrefix(key));
    }

    /**
     * Hash set.
     *
     * @param hashKey         the hash key
     * @param stringStringMap the string string map
     */
//----------------------------------------------Hash---------------------------------------------------
    public void hashSet(String hashKey, Map<String, String> stringStringMap) {
        if (null != stringStringMap && stringStringMap.size() > 0) {
            jedisCluster.hmset(addPrefix(hashKey), stringStringMap);
        }

    }

    /**
     * Hash set long.
     *
     * @param hashKey the hash key
     * @param key     the key
     * @param value   the value
     * @return the long
     */
    public Long hashSet(String hashKey, String key, String value) {
        return jedisCluster.hset(addPrefix(hashKey), key, value);
    }


    /**
     * Hash get string.
     *
     * @param hashKey the hash key
     * @param key     the key
     * @return the string
     */
    public String hashGet(String hashKey, String key) {
        return jedisCluster.hget(addPrefix(hashKey), key);
    }


    /**
     * Hash get list.
     *
     * @param hashKey the hash key
     * @param key     the key
     * @return the list
     */
    public List<String> hashGet(String hashKey, String... key) {
        return jedisCluster.hmget(addPrefix(hashKey), key);
    }

    /**
     * Hash get all map.
     *
     * @param hashKey the hash key
     * @return the map
     */
    public Map<String, String> hashGetAll(String hashKey) {
        return jedisCluster.hgetAll(addPrefix(hashKey));
    }

    /**
     * Hash del.
     *
     * @param hashKey the hash key
     * @param key     the key
     */
    public void hashDel(String hashKey, String... key) {
        jedisCluster.hdel(addPrefix(hashKey), key);
    }

    /**
     * Zadd.
     *
     * @param key             the key
     * @param stringDoubleMap the string double map
     */
//----------------------------------------------zSet-------------------------------------------------
    public void zadd(String key, Map<String, Double> stringDoubleMap) {
        jedisCluster.zadd(addPrefix(key), stringDoubleMap);
    }

    /**
     * Zrange by score with scores set.
     *
     * @param key    the key
     * @param min    the min
     * @param max    the max
     * @param offset the offset
     * @param count  the count
     * @return the set
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return jedisCluster.zrangeByScoreWithScores(addPrefix(key), min, max, offset, count);
    }

    /**
     * Zscan scan result.
     *
     * @param key    the key
     * @param cursor the cursor
     * @param params the params
     * @return the scan result
     */
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return jedisCluster.zscan(addPrefix(key), cursor, params);
    }

    /**
     * Sadd long.
     *
     * @param key    the key
     * @param values the values
     * @return the long
     */
//-----------------------------------------------set------------------------------------------------
    public Long sadd(String key, String... values) {
        if(null != values && values.length > 0 ){
            return jedisCluster.sadd(addPrefix(key), values);
        }else{
            return -1L;
        }
    }

    /**
     * Srem long.
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long srem(String key, String... value) {
        return jedisCluster.srem(addPrefix(key), value);
    }

    /**
     * Smembers set.
     *
     * @param key the key
     * @return the set
     */
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(addPrefix(key));
    }

    /**
     * Srandmember string.
     *
     * @param key the key
     * @return the string
     */
    public String srandmember(String key) {
        return jedisCluster.srandmember(addPrefix(key));
    }
    //------------------------------------------------list-----------------------------------------------

    /**
     * Expire.
     *
     * @param key    the key
     * @param second the second
     */
//----------------------------------------------------other---------------------------------------
    public void expire(String key, int second) {
        jedisCluster.expire(addPrefix(key), second);
    }

    /**
     * Expire at.
     *
     * @param key      the key
     * @param unixTime the unix time
     */
    public void expireAt(String key, long unixTime) {
        jedisCluster.expireAt(addPrefix(key), unixTime);
    }


    /**
     * check key ttl.
     *
     * @param key the key
     * @return the long.  -1:不过期,-2:不存在
     */
    public Long ttl(String key) {
        return jedisCluster.ttl(addPrefix(key));

    }

    /**
     * 移除key的过期时间
     *
     * @param key the key
     * @return long long
     */
    public Long persist(String key) {
        return jedisCluster.persist(addPrefix(key));
    }


    /**
     * Increment long.
     *
     * @param key the key
     * @return the long
     */
    public Long increment(String key) {
        return jedisCluster.incr(addPrefix(key));
    }

    /**
     * Increment long.
     *
     * @param key     the key
     * @param integer the integer
     * @return the long
     */
    public Long increment(String key, long integer) {
        return jedisCluster.incrBy(addPrefix(key), integer);
    }

    /**
     * Increment ex long.
     *
     * @param key     the key
     * @param integer the integer
     * @param seconds the seconds
     * @return the long
     */
    public Long incrementEx(String key, long integer, int seconds) {
        key = addPrefix(key);
        Long incr = jedisCluster.incrBy(key, integer);
        expire(key, seconds);
        return incr;
    }

//------------------------------------------------------lock-----------------------------------------------








    /**
     * Try lock boolean.
     *
     * @param lockKey    String
     * @param value      String
     * @param expireTime String. seconds
     * @return boolean
     */
    public boolean tryLock(String lockKey, String value, String expireTime) {
        lockKey = addPrefix(lockKey);
        String hash = this.loadScript(lockKey, LOCK_SCRIPT);
        List<String> keys = ListUtil.of(lockKey);
        List<String> params = ListUtil.of(value, expireTime);
        try {
            Object result = jedisCluster.evalsha(hash, keys, params);
            return SUCCESS_CODE.equals(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 释放锁
     *
     * @param lockKey the lock key
     * @param lockVal the lock val
     * @return boolean
     */
    public boolean releaseLock(String lockKey, String lockVal) {
        lockKey = addPrefix(lockKey);
        String hash = this.loadScript(lockKey, UNLOCK_SCRIPT);
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);
        List<String> argv = new ArrayList<>();
        argv.add(lockVal);
        try {
            Object result = jedisCluster.evalsha(hash, keys, argv);
            return SUCCESS_CODE.equals(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 加载lua脚本
     *
     * @param lockKey String
     * @param script  String
     * @return String The hash value of the script
     */
    private String loadScript(String lockKey, String script) {
        String hash = CACHE.get(lockKey);
        if (StrUtil.isEmpty(hash) || Boolean.TRUE.equals(!jedisCluster.scriptExists(script, lockKey))) {
            //redis支持脚本缓存，返回哈希码，后续可以继续用来调用脚本
            hash = jedisCluster.scriptLoad(script, lockKey);
            CACHE.put(lockKey, hash);
        }
        return hash;
    }
}
