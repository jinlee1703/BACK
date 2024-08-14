package com.wefood.back.product.repository;

import com.wefood.back.product.dto.ProductImageResponse;
import com.wefood.back.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImage.Pk> {

    @Query("select new com.wefood.back.product.dto.ProductImageResponse(i.name, pi.isThumbnail) from Image i inner join ProductImage pi on i.id=pi.pk.imageId where pi.pk.productId=:productId")
    List<ProductImageResponse> findImageByProductId(@Param("productId") Long productId);
}
