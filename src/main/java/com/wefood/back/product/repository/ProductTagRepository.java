package com.wefood.back.product.repository;

import com.wefood.back.product.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTagRepository extends JpaRepository<ProductTag, ProductTag.Pk> {
}
