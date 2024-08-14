package com.wefood.back.product.repository;

import com.wefood.back.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategory.Pk> {

}
