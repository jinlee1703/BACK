package com.wefood.back.global.image.entity;

import com.wefood.back.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_images")
public class ProductImage{

    @EmbeddedId
    private Pk pk;

    @Column(nullable = false)
    private Boolean isThumbnail;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    @Column
    private Integer sequence;

    @Builder
    public ProductImage(Pk pk, Boolean isThumbnail, Product product, Image image,Integer sequence) {
        this.pk = pk;
        this.isThumbnail = isThumbnail;
        this.product = product;
        this.image = image;
        this.sequence = sequence;
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
