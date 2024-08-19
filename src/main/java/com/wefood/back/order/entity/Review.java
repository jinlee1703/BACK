package com.wefood.back.order.entity;

import com.wefood.back.product.entity.Product;
import com.wefood.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * class: Review.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    @Column(nullable = false)
    private Integer score;

    @Builder
    public Review(OrderDetail orderDetail, Product product, User user, String title, String content,
                  LocalDateTime registeredAt, Integer score) {
        this.orderDetail = orderDetail;
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.registeredAt = registeredAt;
        this.score = score;
    }
}