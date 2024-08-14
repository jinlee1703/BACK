package com.wefood.back.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: Product.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private Integer price;

    @Builder
    public Product(String name, String detail, Integer price) {
        this.name = name;
        this.detail = detail;
        this.price = price;
    }
}