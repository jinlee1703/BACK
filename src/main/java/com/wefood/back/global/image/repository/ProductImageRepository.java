package com.wefood.back.global.image.repository;

import com.wefood.back.global.image.entity.ProductImage;
import com.wefood.back.product.dto.ProductImageDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImage.Pk> {

    @Query("select new com.wefood.back.product.dto.ProductImageDetailResponse(i.name, pi.isThumbnail) from Image i inner join ProductImage pi on i.id=pi.pk.imageId where pi.pk.productId=:productId")
    List<ProductImageDetailResponse> findImageByProductId(@Param("productId") Long productId);

}
