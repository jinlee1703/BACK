package com.wefood.back.product.service;

import com.wefood.back.product.dto.ProductDetailResponse;
import com.wefood.back.product.dto.ProductImageResponse;
import com.wefood.back.product.dto.ProductResponse;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.product.repository.ProductImageRepository;
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
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    /**
     * 단일 상품을 조회하는 메서드
     *
     * @param id
     * @return ProductDetailResponseDto
     */
    public ProductDetailResponse getProductDetail(Long id) {
        Optional<ProductDetailResponse> productDetailResponse = productRepository.findProductDetailByProductId(id);
        if (productDetailResponse.isEmpty()) {
            throw new ProductNotFoundException();
        }
        List<ProductImageResponse> imageByProductId = productImageRepository.findImageByProductId(id);
        for (ProductImageResponse response : imageByProductId) {
            response.setName(imgRoute + "/" + bucketName + "/product/" + id + "/" + response.getName());
        }
        productDetailResponse.get().setImg(imageByProductId);

        return productDetailResponse.get();
    }

    /**
     * 최근 상품
     *
     * @return
     */
    public List<ProductResponse> getProducts() {
        List<ProductResponse> products = productRepository.findTop5ByOrderByIdDesc();
        for (ProductResponse product : products) {
            product.setImg(imgRoute + "/" + bucketName + "/product/" + product.getId() + "/" + product.getImg());
        }
        return products;
    }

    public Page<ProductResponse> getProductByCategory(Long categoryId, Pageable pageable) {
        Page<ProductResponse> products = productRepository.findProductByCategoryId(categoryId, pageable);
        for (ProductResponse product : products) {
            product.setImg(imgRoute + "/" + bucketName + "/product/" + product.getId() + "/" + product.getImg());
        }
        return products;
    }
}
