package com.wefood.back.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: Item.
 *
 * @author JBumLee
 * @version 2024/08/19
 */
@Entity
@Getter
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Builder
    public Item(String name) {
        this.name = name;
    }
}
