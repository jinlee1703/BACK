package com.wefood.back.order.repository;

import com.wefood.back.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {



}
