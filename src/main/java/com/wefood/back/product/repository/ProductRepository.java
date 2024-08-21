package com.wefood.back.product.repository;

import com.wefood.back.farm.dto.FarmInfoResponse;
import com.wefood.back.order.dto.CartProductResponse;
import com.wefood.back.product.dto.ProductDetailResponse;
import com.wefood.back.product.dto.ProductResponse;
import com.wefood.back.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * class: ProductRepository.
 *
 * @author JBumLee
 * @version 2024/08/15
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new com.wefood.back.product.dto.ProductDetailResponse(p.id, p.name, p.detail, p.price, p.item.id) from Product p where p.id=:productId and p.isStatus=true")
    Optional<ProductDetailResponse> findProductDetailByProductId(@Param("productId") Long productId);

    @Query("select new com.wefood.back.product.dto.ProductResponse(p.id, p.name, p.price, i.name) from Product p inner join ProductImage pi on pi.pk.productId=p.id inner join Image i on pi.pk.imageId=i.id where pi.isThumbnail=true and p.isStatus=true order by p.id desc limit 4")
    List<ProductResponse> findTop4ByOrderByIdDesc();

    @Query("select new com.wefood.back.product.dto.ProductResponse(p.id, p.name, p.price, i.name) from Product p inner join ProductCategory c on p.id=c.pk.productId inner join ProductImage pi on pi.pk.productId=p.id inner join Image i on pi.pk.imageId=i.id where c.pk.categoryId=:categoryId and p.isStatus=true and pi.isThumbnail=true")
    Page<ProductResponse> findProductByCategoryId(@Param("categoryId") Long id, Pageable pageable);

    @Query("select new com.wefood.back.product.dto.ProductResponse(p.id, p.name, p.price, i.name) from Product p inner join ProductImage pi on pi.pk.productId=p.id inner join Image i on pi.pk.imageId=i.id where pi.isThumbnail=true and p.isStatus=true and p.name like %:search%")
    Page<ProductResponse> findByNameLike(@Param("search") String search, Pageable pageable);

    @Query("select new com.wefood.back.product.dto.ProductResponse(p.id, p.name, p.price, i.name) from Product p inner join ProductImage pi on pi.pk.productId=p.id inner join Image i on pi.pk.imageId=i.id inner join ProductTag pt on pt.pk.productId=p.id inner join Tag t on t.id=pt.pk.tagId where pi.isThumbnail=true and p.isStatus=true and t.name like :search")
    Page<ProductResponse> findByTag(@Param("search") String search, Pageable pageable);

    @Query("select new com.wefood.back.order.dto.CartProductResponse(p.id, p.name, p.price, i.name, c.quantity) from Product p inner join Cart c on c.pk.productId=p.id inner join ProductImage pi on pi.pk.productId=p.id inner join Image i on pi.pk.imageId=i.id where c.pk.userId=:userId and pi.isThumbnail=true and p.isStatus=true")
    List<CartProductResponse> findCartProductByUserId(@Param("userId") Long userId);

    FarmInfoResponse findFarmById(Long productId);
}
