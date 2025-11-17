package com.example.demo.service

import com.example.demo.model.entity.Product
import com.example.demo.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {
    fun findByCategory(category: String, pageable: Pageable):Page<Product> {
        val page = productRepository.findByCategory(category,pageable)
        if (page.isEmpty) {
            throw IllegalArgumentException("Не найдена такая категория  $category")
        }
        return page
    }
    fun findByPriceBetween(
        min: Double,
        max: Double,
        pageable: Pageable
    ): Page<Product> {
        val page = productRepository.findByPriceBetween(min, max, pageable)
        return page
    }
}