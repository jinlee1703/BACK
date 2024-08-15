package com.wefood.back.product.repository;

import com.wefood.back.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class: ProductRepository.
 *
 * @author JBum
 * @version 2024/08/15
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

}
