package com.wefood.back.order.repository;

import com.wefood.back.order.entity.Cart;
import com.wefood.back.order.entity.Cart.Pk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Pk> {
}
