package com.wefood.back.product.repository;

import com.wefood.back.product.dto.ProductResponseDto;
import com.wefood.back.product.entity.ProductCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategories, ProductCategories.Pk> {

}
