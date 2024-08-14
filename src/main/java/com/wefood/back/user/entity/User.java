package com.wefood.back.user.entity;

import com.wefood.back.user.dto.response.UserGetResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: User.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String phoneNumber;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private Boolean isSeller;
    @Column
    private Boolean isResign;
    @Column
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

    public UserGetResponse convertToUserGetResponse() {
        return new UserGetResponse(this.id, this.phoneNumber, this.password, this.name, this.isSeller, this.isResign, this.accountNumber);
    }

}