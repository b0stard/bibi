package com.example.demo.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductDto(
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val quantity: Int,
    val createdAt: LocalDateTime
)
