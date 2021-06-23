package com.cache.cache.data

import java.util.Date

data class ReceivableUnitRequest(
    val id: Long? = null,
    val externalId: String? = null,
    val brand: String? = null,
    val document: String? = null,
    val finalReceiver: String? = null,
    val scheduledFor: Date? = null,
    val createdAt: Date? = null,
    val liquidValue: Long? = null,
    val status: Int? = null,
    val settlementDate: Date? = null,
    )
