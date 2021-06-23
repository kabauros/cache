package com.cache.cache.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisCluster
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

@Service
class RedisService(val jedisCluster: JedisCluster, @Value("8000") val ttl: Int = 0) {

    fun set(key: String, value: String) {
        try {
            with(jedisCluster) {
                this.set(key, value)
                this.expire(key, ttl)
            }
        } catch (e: Exception) {
            println("RedisService m=set, RedisService - Could not ADD value to redis")
        }
    }

    fun get(key: String): String? {
        try {
            with(jedisCluster) {
                return get(key)
            }
        } catch (e: Exception) {
            println("RedisService m=get, RedisService - Could not GET value to redis")
            return null
        }
    }

    fun del(key: String) {
        try {
            if (!get(key).isNullOrBlank())
                with(jedisCluster) {
                    del(key)
                }
        } catch (e: Exception) {
            println("RedisService m=del, RedisService - Could not GET value to redis")
        }
    }

    fun setWithTTL(key: String, value: String, expireSeconds: Int) {
        try {
            with(jedisCluster) {
                this.set(key, value)
                this.expire(key, expireSeconds)
            }
        } catch (e: Exception) {
            println("RedisService m=setWithTTL, RedisService - Could not ADD value to redis with TTL ")
        }
    }

    fun setExpireToday(key: String, value: String) {
        try {
            val periodAsSeconds = ChronoUnit.SECONDS.between(LocalTime.now(), LocalTime.MAX)
            with(jedisCluster) {
                this.set(key, value)
                this.expire(key, periodAsSeconds.toInt())
            }
        } catch (e: Exception) {
            println("RedisService m=setExpireToday, RedisService - Could not ADD value to redis with TTL ")
        }
    }

    fun setExpireThisMonth(key: String, value: String) {
        try {

            val now = LocalDateTime.now()
            val lastLocalDate = now.withMonth(now.monthValue).with(TemporalAdjusters.lastDayOfMonth())
            val lastLocalTime = LocalTime.MAX
            val lastLocalDateTime = LocalDateTime.of(lastLocalDate.toLocalDate(), lastLocalTime)

            val periodAsSeconds = ChronoUnit.SECONDS.between(now, lastLocalDateTime)

            with(jedisCluster) {
                this.set(key, value)
                this.expire(key, periodAsSeconds.toInt())
            }
        } catch (e: Exception) {
            println("RedisService m=setExpireThisMonth, RedisService - Could not ADD value to redis with TTL ")
        }
    }
}
