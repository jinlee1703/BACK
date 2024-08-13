package com.wefood.back.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: OrderStatus.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderStatus {
    @Id
    @Column(length = 20)
    private String id;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Builder
    public OrderStatus(String id, LocalDate createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }
}