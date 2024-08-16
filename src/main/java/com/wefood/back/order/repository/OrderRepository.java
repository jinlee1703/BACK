package com.wefood.back.order.repository;

import com.wefood.back.order.dto.response.OrderGetResponse;
import com.wefood.back.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<OrderGetResponse> findAllByUserId(Long userId);

    OrderGetResponse findById(Long orderId);

}
