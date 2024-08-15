package com.wefood.back.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: ProductCategory.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
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
