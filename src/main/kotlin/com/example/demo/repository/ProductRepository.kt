package com.example.demo.repository

import com.example.demo.model.entity.Product
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>{
    fun findByCategory(category: String, pageable: Pageable) : Page<Product>
    fun findByPriceBetween(min: Double, max: Double, pageable: Pageable):Page<Product>

    @Query("SELECT p FROM Product p ORDER BY p.id ASC")
    fun findByPage(pageable: Pageable): Page<Product>
    @Query("SELECT p FROM Product p WHERE p.id > :lastId ORDER BY p.id ASC")
    fun findAfterId(
        @Param("lastId") lastId: Long,
        pageable: Pageable
    ): List<Product>
}