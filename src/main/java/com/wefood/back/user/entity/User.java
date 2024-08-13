package com.wefood.back.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: User.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Boolean isSeller;

    @Column(nullable = false)
    private Boolean isResign;

    @Column(length = 14)
    private String accountNumber;

    @Builder
    public User(String phoneNumber, String password, String name, Boolean isSeller,
        Boolean isResign, String accountNumber) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.isSeller = isSeller;
        this.isResign = isResign;
        this.accountNumber = accountNumber;
    }
}