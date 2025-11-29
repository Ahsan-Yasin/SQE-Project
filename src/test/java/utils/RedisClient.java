package utils;

import redis.clients.jedis.Jedis;

public class RedisClient {

    private final String host;
    private final int port;

    public RedisClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void set(String key, String value) {
        try (Jedis jedis = new Jedis(host, port)) {
            jedis.set(key, value);
        } catch (Exception e) {
            System.out.println("Redis set failed: " + e.getMessage());
        }
    }

    public String get(String key) {
        try (Jedis jedis = new Jedis(host, port)) {
            return jedis.get(key);
        } catch (Exception e) {
            System.out.println("Redis get failed: " + e.getMessage());
            return null;
        }
    }
}
