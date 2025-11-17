package com.example.demo.pagination

import com.example.demo.model.entity.Product
import com.example.demo.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class KeyBasedPagination(
    private val productRepository: ProductRepository
) {
    fun getNextPage(lastSeenId: Long?, size: Int): List<Product> {
        val pageable: Pageable = PageRequest.of(0, size)

        return if (lastSeenId == null) {
            productRepository.findByPage(pageable).content
        } else {
            productRepository.findAfterId(lastSeenId, pageable)
        }
    }
}
