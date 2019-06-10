package com.cxs.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/27 12:54
 */
public class RedisClient {

    /**
     * redis池
     */
    private JedisPool pool;
    /**
     * redis集群
     */
    private JedisCluster jedisCluster;


    public RedisClient(JedisPool pool) {
        this.pool = pool;
    }

    public RedisClient(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void set(String key, String value) {
        if (jedisCluster != null) {
            jedisCluster.set(key, value);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.set(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }

        }
    }

    public void set(byte[] key, byte[] value) {
        if (jedisCluster != null) {
            jedisCluster.set(key, value);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.set(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        String value = null;
        if (jedisCluster != null) {
            value = jedisCluster.get(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                value = jedis.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return value;
    }

    public byte[] get(byte[] key) {
        byte[] value = null;
        if (jedisCluster != null) {
            value = jedisCluster.get(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                value = jedis.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return value;
    }

    public Long incre(String key) {
        Long value = 0L;
        if (jedisCluster != null) {
            value = jedisCluster.incr(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                value = jedis.incr(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return value;
    }

    public void setExpire(String key, long timeOut) {
        if (jedisCluster != null) {
            jedisCluster.expire(key, (int) timeOut);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.expire(key, (int) timeOut);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    public void del(String... key) {
        if (jedisCluster != null) {
            jedisCluster.del(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.del(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    public void del(byte[]... key) {
        if (jedisCluster != null) {
            jedisCluster.del(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.del(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }


    public boolean isExsit(String key) {
        boolean flag = false;
        if (jedisCluster != null) { // 优先使用集群版的redis 做
            flag = jedisCluster.exists(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                flag = jedis.exists(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return flag;
    }

    public boolean isExist(byte[] key) {
        boolean flag = false;
        if (jedisCluster != null) { // 优先使用集群版的redis 做
            flag = jedisCluster.exists(key);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                flag = jedis.exists(key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return flag;
    }

    public boolean set(String key, int pos, boolean flag) {
        Boolean f = false;
        if (jedisCluster != null) {
            f = jedisCluster.setbit(key, pos, flag);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                f = jedis.setbit(key, pos, flag);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return f;
    }

    public boolean get(String key, int pos) {
        boolean f = false;
        if (jedisCluster != null) {
            f = jedisCluster.getbit(key, pos);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                f = jedis.getbit(key, pos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return f;
    }


    public void zadd(String goodsList, Long score, String goodsNum) {
        if (jedisCluster != null) {
            jedisCluster.zadd(goodsList, score.doubleValue(), goodsNum);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.zadd(goodsList, score.doubleValue(), goodsNum);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    public boolean hashExit(String goodsInfo, String goodsId) {
        boolean f = false;
        if (jedisCluster != null) {
            f = jedisCluster.hexists(goodsInfo, goodsId);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                f = jedis.hexists(goodsInfo, goodsId);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return f;
    }

    public String getHash(String goodsInfo, String goodsId) {
        String value = null;
        if (jedisCluster != null) {
            value = jedisCluster.hget(goodsInfo, goodsId);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                value = jedis.hget(goodsInfo, goodsId);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return value;
    }


    public void hset(String goodsInfo, String goodsId, String goodsNum) {
        if (jedisCluster != null) {
            jedisCluster.hset(goodsInfo, goodsId, goodsNum);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.hset(goodsInfo, goodsId, goodsNum);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    public Map<String, String> hgetAll(String goodsInfo) {
        Map<String, String> value = null;
        if (jedisCluster != null) {
            value = jedisCluster.hgetAll(goodsInfo);
        } else {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                value = jedis.hgetAll(goodsInfo);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return value;
    }
}

