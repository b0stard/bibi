package com.example.demo.pagination

import com.example.demo.model.entity.Product
import com.example.demo.repository.ProductRepository
import org.springframework.data.domain.PageRequest

class ClassicPagination(
    private val productRepository: ProductRepository
) {
    fun getPage(pageNumber: Int, pageSize: Int): List<Product> {
        val pageable = PageRequest.of(pageNumber, pageSize)
        val page = productRepository.findAll(pageable)

        if (page.content.isEmpty()) {
            throw IllegalArgumentException("Не найдена страница $pageNumber с размером $pageSize")
        }
        return page.content
    }
}