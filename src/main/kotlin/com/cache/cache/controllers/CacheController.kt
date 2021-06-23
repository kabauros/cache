package com.cache.cache.controllers

import com.cache.cache.data.SetRequest
import com.cache.cache.services.CacheService
import com.cache.cache.services.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/redis")
class CacheController (){

    @Autowired
    lateinit var cacheService: CacheService

    @GetMapping("/get/{key}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getByKey (@PathVariable key: String): String {
        return cacheService.getByKey(key)
    }

    @PostMapping("/set", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun setToRedis ( @RequestBody request: SetRequest) {
        cacheService.set(request.key, request.value)
    }

    @PostMapping("/set/{ttl}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun setToRedis (@PathVariable ttl: Int, @RequestBody request: SetRequest) {
        cacheService.setWithTTL(request.key, request.value, ttl)
    }

    @DeleteMapping("/del", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delToRedis ( @RequestBody request: SetRequest) {
        cacheService.del(request.key)
    }
}