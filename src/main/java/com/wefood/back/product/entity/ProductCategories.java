package com.wefood.back.product.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "product_categories")
public class ProductCategories {

    @EmbeddedId
    private Pk pk;

    @NoArgsConstructor
    @Getter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "product_id")
        private Long productId;

        @Column(name = "category_id")
        private Long categoryId;

        @Builder
        public Pk(Long productId, Long categoryId) {
            this.categoryId = categoryId;
            this.productId = productId;
        }
    }
}
