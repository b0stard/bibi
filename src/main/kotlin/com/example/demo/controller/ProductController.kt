package com.example.demo.controller

import com.example.demo.model.entity.Product
import com.example.demo.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class ProductController(
    val productService: ProductService
)
{
    @GetMapping("/category")
    fun getProductsByCategory(
        @RequestParam category: String,
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Product>> {
        val page = productService.findByCategory(
            category,
            PageRequest.of(pageNumber, pageSize)
        )
        return ResponseEntity.ok(page)
    }

    @GetMapping("/price")
    fun getProductsByPriceRange(
        @RequestParam min: Double,
        @RequestParam max: Double,
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Product>> {
        val page = productService.findByPriceBetween(
            min,
            max,
            PageRequest.of(pageNumber, pageSize)
        )
        return ResponseEntity.ok(page)
    }
}