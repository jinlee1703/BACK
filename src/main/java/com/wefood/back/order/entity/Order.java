package com.wefood.back.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: Order.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer totalCost;

    private LocalDate deliveryDate;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(length = 5)
    private String invoiceNumber;

    @Column(nullable = false, length = 30)
    private String receiverName;

    @Column(length = 100)
    private String receiverAddress;

    @Column(nullable = false, length = 11)
    private String receiverPhoneNumber;

    private LocalDateTime meetingAt;

    @Builder
    public Order(OrderStatus orderStatus, Integer totalCost, LocalDate deliveryDate,
        LocalDate orderDate, String invoiceNumber, String receiverName, String receiverAddress,
        String receiverPhoneNumber, LocalDateTime meetingAt) {
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.invoiceNumber = invoiceNumber;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.meetingAt = meetingAt;
    }
}