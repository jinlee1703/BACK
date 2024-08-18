package com.wefood.back.order.entity;

import com.wefood.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * class: Order.
 *
 * @author JBumLee
 * @version 2024/08/14
 */
@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer totalCost;

    @Column
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(length = 5)
    private String invoiceNumber;

    @Column(nullable = false, length = 30)
    private String receiverName;

    @Column(length = 100)
    private String receiverAddress;

    @Column(length = 100)
    private String receiverAddressDetail;

    @Column(nullable = false, length = 11)
    private String receiverPhoneNumber;

    @Column
    private LocalDateTime meetingAt;

    @Builder
    public Order(User user, OrderStatus orderStatus, Integer totalCost, LocalDate deliveryDate,
                 LocalDate orderDate, String invoiceNumber, String receiverName, String receiverAddress,
                 String receiverAddressDetail, String receiverPhoneNumber, LocalDateTime meetingAt) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.totalCost = totalCost;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.invoiceNumber = invoiceNumber;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverAddressDetail = receiverAddressDetail;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.meetingAt = meetingAt;
    }
}