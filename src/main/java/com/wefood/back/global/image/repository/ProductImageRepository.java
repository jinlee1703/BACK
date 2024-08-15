package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.Image;
import com.wefood.back.global.image.entity.ProductImage;
import com.wefood.back.global.image.entity.ProductImage.Pk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * class: ProductImageRepository.
 *
 * @author JBum
 * @version 2024/08/14
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Pk>{
}
