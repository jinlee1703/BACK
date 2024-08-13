package com.wefood.back.product.repository;

import com.wefood.back.product.dto.ProductResponseDto;
import com.wefood.back.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductResponseDto> findTop5ByOrderByIdDesc();

    @Query("select p.id, p.name, p.price from Product p join ProductCategories c on p.id=c.pk.productId where c.pk.categoryId=:categoryId")
    Page<ProductResponseDto> findProductByCategoryId(@Param("categoryId") Long id, Pageable pageable);
}
