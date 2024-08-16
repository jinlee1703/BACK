package com.wefood.back.order.repository;

import com.wefood.back.order.dto.response.OrderDetailGetResponse;
import com.wefood.back.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetailGetResponse> findByOrderId(Long orderId);

}
