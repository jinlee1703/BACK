package com.wefood.back.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: Address.
 *
 * @author JBum
 * @version 2024/08/13
 */
@Entity
@Getter
@Table(name = "addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 5)
    private String zoneNo;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 50)
    private String detail;

    @Builder
    public Address(User user, String zoneNo, String address, String detail) {
        this.user = user;
        this.zoneNo = zoneNo;
        this.address = address;
        this.detail = detail;
    }
}