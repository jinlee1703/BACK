package com.wefood.back.product.service;

import com.wefood.back.product.dto.ProductDetailResponseDto;
import com.wefood.back.product.dto.ProductResponseDto;
import com.wefood.back.product.entity.Product;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 단일 상품을 조회하는 메서드
     *
     * @param id
     * @return ProductDetailResponseDto
     */
    public ProductDetailResponseDto getProductDetail(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return ProductDetailResponseDto.builder().id(id).name(product.getName()).detail(product.getDetail()).price(product.getPrice()).build();
    }

    /**
     * 최근 상품
     *
     * @return
     */
    public List<ProductResponseDto> getProducts() {
        return productRepository.findTop5ByOrderByIdDesc();
    }

    public Page<ProductResponseDto> getProductByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findProductByCategoryId(categoryId, pageable);
    }
}
