package com.wefood.back.product.service;

import com.wefood.back.global.image.repository.ProductImageRepository;
import com.wefood.back.product.dto.ProductDetailResponse;
import com.wefood.back.product.dto.ProductImageResponse;
import com.wefood.back.product.dto.ProductResponse;
import com.wefood.back.product.exception.CategoryNotFoundException;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.product.repository.CategoryRepository;
import com.wefood.back.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final String imgRoute = "https://s3.ap-northeast-2.amazonaws.com";
    private static final String productURL = "/product/";
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * 단일 상품을 조회하는 메서드
     *
     * @param id productId
     * @return ProductDetailResponseDto
     */
    public ProductDetailResponse getProductDetail(Long id) {
        Optional<ProductDetailResponse> productDetailResponse = productRepository.findProductDetailByProductId(id);
        if (productDetailResponse.isEmpty()) {
            throw new ProductNotFoundException();
        }
        List<ProductImageResponse> imageByProductId = productImageRepository.findImageByProductId(id);
        for (ProductImageResponse response : imageByProductId) {
            response.setName(imgRoute + "/" + bucketName + productURL + id + "/" + response.getName());
        }
        productDetailResponse.get().setImg(imageByProductId);

        return productDetailResponse.get();
    }

    /**
     * 최근 상품 4개 조회
     *
     * @return product list
     */
    public List<ProductResponse> getProducts() {
        List<ProductResponse> products = productRepository.findTop4ByOrderByIdDesc();
        for (ProductResponse product : products) {
            product.setImg(imgRoute + "/" + bucketName + productURL + product.getId() + "/" + product.getImg());
        }
        return products;
    }

    /**
     * 카테고리별 상품 조회
     *
     * @param categoryId category
     * @param pageable Page
     * @return Page 별 product
     */
    public Page<ProductResponse> getProductByCategory(Long categoryId, Pageable pageable) {
        if (categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException();
        }

        Page<ProductResponse> products = productRepository.findProductByCategoryId(categoryId, pageable);
        for (ProductResponse product : products) {
            product.setImg(imgRoute + "/" + bucketName + productURL + product.getId() + "/" + product.getImg());
        }
        return products;
    }
}
