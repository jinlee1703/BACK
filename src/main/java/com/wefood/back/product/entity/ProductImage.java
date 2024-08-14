package com.wefood.back.product.entity;

import com.wefood.back.global.image.entity.Image;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "product_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @EmbeddedId
    private Pk pk;

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail;

    @ManyToOne
    @MapsId(value = "imageId")
    private Image image;

    @ManyToOne
    @MapsId(value = "productId")
    private Product product;

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
