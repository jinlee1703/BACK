package com.wefood.back.order.entity;

import com.wefood.back.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

/**
 * class: OrderDetial.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@Setter
@Table(name = "order_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Builder
    public OrderDetail(Order order, Product product, Review review, Integer quantity, Integer price) {
        this.review = review;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}