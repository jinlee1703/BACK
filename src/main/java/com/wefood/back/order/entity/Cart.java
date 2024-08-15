package com.wefood.back.order.entity;

import com.wefood.back.product.entity.Product;
import com.wefood.back.user.entity.User;
import jakarta.persistence.*;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: Cart.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @EmbeddedId
    private Pk pk;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Builder
    public Cart(Pk pk, Integer quantity, Product product, User user) {
        this.pk = pk;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "user_id")
        private Long userId;

        @Builder
        public Pk(Long productId, Long userId) {
            this.productId = productId;
            this.userId = userId;
        }
    }
}