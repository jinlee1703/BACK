package com.wefood.back.order.service;

import com.wefood.back.order.dto.CartProductResponse;
import com.wefood.back.order.dto.request.*;
import com.wefood.back.order.dto.response.OrderDetailGetResponse;
import com.wefood.back.order.dto.response.OrderGetResponse;
import com.wefood.back.order.dto.response.ReviewGetResponse;
import com.wefood.back.order.entity.Order;
import com.wefood.back.order.entity.OrderDetail;
import com.wefood.back.order.entity.OrderStatus;
import com.wefood.back.order.entity.Review;
import com.wefood.back.order.repository.OrderDetailRepository;
import com.wefood.back.order.repository.OrderRepository;
import com.wefood.back.order.repository.OrderStatusRepository;
import com.wefood.back.order.repository.ReviewRepository;
import com.wefood.back.product.entity.Product;
import com.wefood.back.product.repository.ProductRepository;
import com.wefood.back.user.entity.User;
import com.wefood.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    public List<ReviewGetResponse> findAllReview(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public ReviewGetResponse findReview(Long id) {
        return reviewRepository.queryById(id);
    }

    public void createOrder(Long userId, DirectOrderCreateRequest orderCreateRequest) {

        OrderStatus orderStatus = orderStatusRepository.findById("주문완료").get();

        User user = userRepository.findById(userId).get();

        // 직거래랑 , 온라인주문이랑 차이를 둬서 해야 함
        // orderDetail 을 추가해야함

        Order order;

        if (orderCreateRequest.deliveryDate() != null) {
            order = Order.builder()
                    .user(user)
                    .orderStatus(orderStatus)
                    .totalCost(orderCreateRequest.totalCost())
                    .orderDate(LocalDate.now())
                    .deliveryDate(orderCreateRequest.deliveryDate().plusDays(3L))
                    .invoiceNumber(orderCreateRequest.invoiceNumber())
                    .meetingAt(orderCreateRequest.meetingAt())
                    .receiverAddress(orderCreateRequest.receiverAddress())
                    .receiverAddressDetail(orderCreateRequest.receiverAddressDetail())
                    .receiverName(orderCreateRequest.receiverName())
                    .receiverPhoneNumber(orderCreateRequest.receiverPhoneNumber())
                    .build();
        } else {
            order = Order.builder()
                    .user(user)
                    .orderStatus(orderStatus)
                    .totalCost(orderCreateRequest.totalCost())
                    .orderDate(LocalDate.now())
                    .deliveryDate(null)
                    .invoiceNumber(orderCreateRequest.invoiceNumber())
                    .meetingAt(orderCreateRequest.meetingAt())
                    .receiverAddress(orderCreateRequest.receiverAddress())
                    .receiverAddressDetail(orderCreateRequest.receiverAddressDetail())
                    .receiverName(orderCreateRequest.receiverName())
                    .receiverPhoneNumber(orderCreateRequest.receiverPhoneNumber())
                    .build();
        }


        Order createOrder = orderRepository.save(order);
        Product product = productRepository.findById(orderCreateRequest.productId()).get();
        OrderDetail orderDetail = new OrderDetail(createOrder, product, null, orderCreateRequest.quantity(), orderCreateRequest.price());
        orderDetailRepository.save(orderDetail);
    }

    public void createBasketOrder(Long userId, BasketOrderRequest basketOrderRequest) {

        OrderStatus orderStatus = orderStatusRepository.findById("주문완료").get();

        User user = userRepository.findById(userId).get();

        // 직거래랑 , 온라인주문이랑 차이를 둬서 해야 함
        // orderDetail 을 추가해야함

        Order order;

        OrderCreateRequest orderCreateRequest = basketOrderRequest.orderCreateRequest();

        if (orderCreateRequest.deliveryDate() != null) {
            order = Order.builder()
                    .user(user)
                    .orderStatus(orderStatus)
                    .totalCost(orderCreateRequest.totalCost())
                    .orderDate(LocalDate.now())
                    .deliveryDate(orderCreateRequest.deliveryDate().plusDays(3L))
                    .invoiceNumber(orderCreateRequest.invoiceNumber())
                    .meetingAt(orderCreateRequest.meetingAt())
                    .receiverAddress(orderCreateRequest.receiverAddress())
                    .receiverAddressDetail(orderCreateRequest.receiverAddressDetail())
                    .receiverName(orderCreateRequest.receiverName())
                    .receiverPhoneNumber(orderCreateRequest.receiverPhoneNumber())
                    .build();
        } else {
            order = Order.builder()
                    .user(user)
                    .orderStatus(orderStatus)
                    .totalCost(orderCreateRequest.totalCost())
                    .orderDate(LocalDate.now())
                    .deliveryDate(null)
                    .invoiceNumber(orderCreateRequest.invoiceNumber())
                    .meetingAt(orderCreateRequest.meetingAt())
                    .receiverAddress(orderCreateRequest.receiverAddress())
                    .receiverAddressDetail(orderCreateRequest.receiverAddressDetail())
                    .receiverName(orderCreateRequest.receiverName())
                    .receiverPhoneNumber(orderCreateRequest.receiverPhoneNumber())
                    .build();
        }


        Order createOrder = orderRepository.save(order);

        for (int i = 0; i < basketOrderRequest.farms().size(); i++) {

            CartRequest cartRequest = basketOrderRequest.farms().get(i);

            for (int j = 0; j < cartRequest.products().size(); j++) {
                CartProductResponse cartProductResponse = cartRequest.products().get(j);
                Product product = productRepository.findById(cartProductResponse.getId()).get();
                OrderDetail orderDetail = new OrderDetail(createOrder, product, null, cartProductResponse.getQuantity(), cartProductResponse.getPrice());
                orderDetailRepository.save(orderDetail);
            }
        }

    }

    public List<OrderGetResponse> findOrderList(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderDetailGetResponse> findOrder(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public void createReview(ReviewCreateRequest createRequest, Long orderDetailId) {

        OrderDetail orderDetail = orderDetailRepository.queryById(orderDetailId);
        Product product = productRepository.findById(orderDetail.getProduct().getId()).get();
        User user = userRepository.findById(orderDetail.getOrder().getUser().getId()).get();
        Review resultReview = reviewRepository.save(new Review(orderDetail, product, user, createRequest.title(), createRequest.content(), LocalDateTime.now(), createRequest.score()));
        orderDetail.setReview(resultReview);
        orderDetailRepository.save(orderDetail);
    }

}
