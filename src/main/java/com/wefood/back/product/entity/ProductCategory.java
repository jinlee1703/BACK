package com.wefood.back.product.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * class: ProductCategory.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Table(name = "product_categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {
  
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId(value = "categoryId")
    private Category category;

    @ManyToOne
    @MapsId(value = "productId")

    private Product product;

    @Builder
    public ProductCategory(Pk pk, Category category, Product product) {
        this.pk = pk;
        this.category = category;
        this.product = product;
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "category_id")
        private Long categoryId;

        @Builder
        public Pk(Long productId, Long categoryId) {
            this.productId = productId;
            this.categoryId = categoryId;
        }
    }
}
