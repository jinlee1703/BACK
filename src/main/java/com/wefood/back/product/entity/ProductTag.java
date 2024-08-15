package com.wefood.back.product.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: ProductTag.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Table(name = "product_tags")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTag {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private Tag tag;

    @Builder
    public ProductTag(Pk pk, Product product, Tag tag) {
        this.pk = pk;
        this.product = product;
        this.tag = tag;
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "tag_id")
        private Long tagId;

        @Builder
        public Pk(Long productId, Long tagId) {
            this.productId = productId;
            this.tagId = tagId;
        }
    }
}