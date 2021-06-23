package com.cache.cache.services

import com.cache.cache.data.ReceivableUnitRequest
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CacheService {

    @Autowired
    lateinit var redisService: RedisService

    fun set(key: String, value: String){
        redisService.set(key, value)
    }

    fun getByKey(key: String) : String{
        val value = redisService.get(key) ?: return "Chave não encontrada"
        return value
    }

    fun setObj(key: String, receivableUnitRequest: ReceivableUnitRequest){
        val gson = Gson()
        val receivableUnitRequestJson: String = gson.toJson(receivableUnitRequest)
        redisService.set(key, receivableUnitRequestJson)
    }

    fun getObjByKey(key: String) : ReceivableUnitRequest? {
        val gson = Gson()
        val redisGetJson = redisService.get(key)?: return null
        return gson.fromJson(redisGetJson, ReceivableUnitRequest::class.java)
    }

    fun setWithTTL(key: String, value: String, ttl: Int){
        redisService.setWithTTL(key, value, ttl)
    }

    fun del(key: String){
        redisService.del(key)
    }

}