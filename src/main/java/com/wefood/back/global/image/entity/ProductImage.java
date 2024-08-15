package com.wefood.back.global.image.entity;

import com.wefood.back.product.entity.Product;
import jakarta.persistence.*;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: ProductImage.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Table(name = "product_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @EmbeddedId
    private Pk pk;

    @Column(name = "is_thumbnail", nullable = false)
    private Boolean isThumbnail;

    @ManyToOne
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @MapsId(value = "imageId")
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    @Builder
    public ProductImage(Pk pk, Boolean isThumbnail, Product product, Image image) {
        this.pk = pk;
        this.isThumbnail = isThumbnail;
        this.product = product;
        this.image = image;
    }


    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "image_id")
        private Long imageId;

        @Builder
        public Pk(Long productId, Long imageId) {
            this.productId = productId;
            this.imageId = imageId;
        }
    }
}
