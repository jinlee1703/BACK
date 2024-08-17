package com.wefood.back.product.entity;

import com.wefood.back.farm.entity.Farm;
import com.wefood.back.global.type.ImageRootType;
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

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @Builder
    public Product(String name, String detail, Integer price, Farm farm) {
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.farm = farm;
    }
}