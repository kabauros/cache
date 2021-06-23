package com.cache.cache.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.JedisCluster

@Configuration
class RedisConfig {
    @Value("localhost")
    lateinit var host: String

    @Value("7000")
    val port: Int = 0

    @Bean
    fun jedisCluster(): JedisCluster {
        return JedisCluster(HostAndPort(host, port))
    }

    @Bean
    fun jedisConnectionFactory() = JedisConnectionFactory(RedisClusterConfiguration(listOf("$host:$port")))

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }
}
