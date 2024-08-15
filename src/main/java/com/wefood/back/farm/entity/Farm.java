package com.wefood.back.farm.entity;

import com.wefood.back.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: Farm.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Table(name = "farms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String detail;

    @Builder
    public Farm(User user, String name, String detail) {
        this.user = user;
        this.detail = detail;
    }
}