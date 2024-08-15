package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.ProductImage;
import com.wefood.back.product.dto.ProductImageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * class: ProductImageRepository.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
public interface ProductImageRepository extends JpaRepository<ProductImage, Pk> {

    @Query("select new com.wefood.back.product.dto.ProductImageResponse(i.name, pi.isThumbnail) from Image i inner join ProductImage pi on i.id=pi.pk.imageId where pi.pk.productId=:productId")
    List<ProductImageResponse> findImageByProductId(@Param("productId") Long productId);
}
