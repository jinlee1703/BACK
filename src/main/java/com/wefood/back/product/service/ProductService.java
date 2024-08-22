package com.wefood.back.product.service;

import com.wefood.back.farm.dto.FarmInfoResponse;
import com.wefood.back.global.image.repository.FarmImageRepository;
import com.wefood.back.global.image.repository.ProductImageRepository;
import com.wefood.back.product.dto.ProductDetailResponse;
import com.wefood.back.global.image.dto.ImageDetailResponse;
import com.wefood.back.product.dto.ProductResponse;
import com.wefood.back.product.exception.CategoryNotFoundException;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.product.repository.CategoryRepository;
import com.wefood.back.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@jakarta.transaction.Transactional
public class ProductService {

    @Value("${wefood.config.image.address}")
    private String imgRoute;
    @Value("${wefood.config.image.productURL}")
    private String productURL;
    @Value("${wefood.config.image.farmURL}")
    private String farmURL;
    private final String slash = "/";

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    private final FarmImageRepository farmImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository, CategoryRepository categoryRepository, FarmImageRepository farmImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
        this.farmImageRepository = farmImageRepository;
    }

    /**
     * 단일 상품을 조회하는 메서드
     *
     * @param id productId
     * @return ProductDetailResponseDto
     */
    @Transactional(readOnly = true)
    public ProductDetailResponse getProductDetail(Long id) {
        Optional<ProductDetailResponse> productDetailResponse = productRepository.findProductDetailByProductId(id);
        if (productDetailResponse.isEmpty()) {
            throw new ProductNotFoundException();
        }
        List<ImageDetailResponse> productImages = productImageRepository.findImageByProductId(id);
        for (ImageDetailResponse image : productImages) {
            image.setName(imgRoute + productURL + slash + id + slash + image.getImg());
        }
        productDetailResponse.get().setProductImg(productImages);

        FarmInfoResponse farm = productRepository.findFarmById(id);
        List<ImageDetailResponse> farmImages = farmImageRepository.findByPk_FarmId(farm.getFarm().getId());
        for (ImageDetailResponse image : farmImages) {
            image.setName(imgRoute + farmURL + slash + farm.getFarm().getId() + slash + image.getImg());
        }
        productDetailResponse.get().setFarmImg(farmImages);
        productDetailResponse.get().setFarmId(farm.getFarm().getId());
        productDetailResponse.get().setFarmName(farm.getFarm().getName());
        productDetailResponse.get().setFarmDetail(farm.getFarm().getDetail());

        return productDetailResponse.get();
    }

    /**
     * 최근 상품 4개 조회
     *
     * @return product list
     */
    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        List<ProductResponse> products = productRepository.findTop4ByOrderByIdDesc();
        for (ProductResponse product : products) {
            product.setImg(imgRoute + productURL + slash + product.getId() + slash + product.getImg());
        }
        return products;
    }

    /**
     * 카테고리별 상품 조회
     *
     * @param categoryId category
     * @param pageable   Page
     * @return Page 별 product
     */
    @Transactional(readOnly = true)
    public Page<ProductResponse> getProductByCategory(Long categoryId, Pageable pageable) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException();
        }

        Page<ProductResponse> products = productRepository.findProductByCategoryId(categoryId, pageable);
        for (ProductResponse product : products) {
            product.setImg(imgRoute + productURL + slash + product.getId() + slash + product.getImg());
        }
        return products;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getProductBySearch(String search, Pageable pageable) {
        Page<ProductResponse> products = productRepository.findByNameLike(search, pageable);
        for (ProductResponse product : products) {
            product.setImg(imgRoute + productURL + slash + product.getId() + slash + product.getImg());
        }

        return products;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getProductByTag(String search, Pageable pageable) {
        Page<ProductResponse> products = productRepository.findByTag(search.replace("#", ""), pageable);
        for (ProductResponse product : products) {
            product.setImg(imgRoute + productURL + slash + product.getId() + slash + product.getImg());
        }

        return products;
    }
}
