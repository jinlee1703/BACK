package com.wefood.back.product.controller;


import com.wefood.back.global.Message;
import com.wefood.back.product.dto.ProductDetailResponse;
import com.wefood.back.product.service.ProductService;
import com.wefood.back.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 최신 상품 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Message<List<ProductResponse>>> getProducts() {
        Message<List<ProductResponse>> message = new Message<>(200, "", productService.getProducts());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 카테고리별 상품 조회
     * @param categoryId
     * @param pageable
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Message<Page<ProductResponse>>> getProductByCategory(@PathVariable(name = "categoryId") Long categoryId, Pageable pageable) {
        Message<Page<ProductResponse>> message = new Message<>(200, "", productService.getProductByCategory(categoryId, pageable));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 상품 상세 조회
     *
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Message<ProductDetailResponse>> getProduct(@PathVariable(name = "productId") Long productId) {
        Message<ProductDetailResponse> message = new Message<>(200, "", productService.getProductDetail(productId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
