package com.wefood.back.product.entity;

import com.wefood.back.global.type.ImageRootType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: Product.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product implements ImageRootType {
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