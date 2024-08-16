package com.wefood.back.order.service;

import com.wefood.back.order.dto.request.OrderCreateRequest;
import com.wefood.back.order.dto.response.OrderDetailGetResponse;
import com.wefood.back.order.dto.response.OrderGetResponse;
import com.wefood.back.order.entity.Order;
import com.wefood.back.order.entity.OrderStatus;
import com.wefood.back.order.repository.OrderDetailRepository;
import com.wefood.back.order.repository.OrderRepository;
import com.wefood.back.order.repository.OrderStatusRepository;
import com.wefood.back.user.entity.User;
import com.wefood.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    public void createOrder(Long userId, OrderCreateRequest orderCreateRequest) {

        OrderStatus orderStatus = orderStatusRepository.findById(orderCreateRequest.orderStatus()).get();

        User user = userRepository.findById(userId).get();

        // 직거래랑 , 온라인주문이랑 차이를 둬서 해야 함
        // meetingAt 똑바로 컨버팅 안되는 문제가 있음
        // orderDetail 을 추가해야함
        Order order = Order.builder()
                .user(user)
                .orderStatus(orderStatus)
                .totalCost(orderCreateRequest.totalCost())
                .orderDate(LocalDate.now())
                .deliveryDate(orderCreateRequest.deliveryDate())
                .invoiceNumber(orderCreateRequest.invoiceNumber())
                .meetingAt(orderCreateRequest.metingAt())
                .receiverAddress(orderCreateRequest.receiverPhoneNumber())
                .receiverName(orderCreateRequest.receiverName())
                .receiverPhoneNumber(orderCreateRequest.receiverPhoneNumber())
                .build();
        orderRepository.save(order);
    }

    public List<OrderGetResponse> findOrderList(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderDetailGetResponse> findOrder(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

}
