package com.cache.cache.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CacheService {

    @Autowired
    lateinit var redisService: RedisService

    fun getByKey(key: String) : String{
       val value = redisService.get(key) ?: return "Chave não encontrada";
        return value
    }

    fun set(key: String, value: String){
        redisService.set(key, value)
    }

    fun setWithTTL(key: String, value: String, ttl: Int){
        redisService.setWithTTL(key, value, ttl)
    }

    fun del(key: String){
        redisService.del(key)
    }

}