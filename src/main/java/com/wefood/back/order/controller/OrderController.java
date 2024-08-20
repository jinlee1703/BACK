package com.wefood.back.order.controller;


import com.wefood.back.global.Message;
import com.wefood.back.order.dto.request.BasketOrderRequest;
import com.wefood.back.order.dto.request.DirectOrderCreateRequest;
import com.wefood.back.order.dto.request.ReviewCreateRequest;
import com.wefood.back.order.dto.response.OrderDetailGetResponse;
import com.wefood.back.order.dto.response.OrderGetResponse;
import com.wefood.back.order.dto.response.ReviewGetResponse;
import com.wefood.back.order.repository.OrderDetailRepository;
import com.wefood.back.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    private final OrderDetailRepository orderDetailRepository;


    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@PathVariable Long userId, @RequestBody DirectOrderCreateRequest orderCreateRequest) {
        orderService.createOrder(userId, orderCreateRequest);
    }

    @PostMapping("/{userId}/basket")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBasketOrder(@PathVariable Long userId, @RequestBody BasketOrderRequest basketOrderRequest) {
        orderService.createBasketOrder(userId, basketOrderRequest);
    }

    @GetMapping("/{id}")
    public Message<List<OrderDetailGetResponse>> getOrder(@PathVariable Long id) {
        return new Message<>(200, "주문조회", orderService.findOrder(id));
    }

    @GetMapping
    public Message<List<OrderGetResponse>> getAllOrders(@RequestParam Long userId) {
        return new Message<>(200, "주문리스트 조회", orderService.findOrderList(userId));
    }

    @GetMapping("/review")
    public Message<List<ReviewGetResponse>> getAllReview(@RequestParam Long userId) {
        return new Message<>(200, "리뷰리스트 조회", orderService.findAllReview(userId));
    }

    @GetMapping("/review-spec")
    public Message<ReviewGetResponse> getReview(@RequestParam Long id) {
        return new Message<>(200, "리뷰 조회", orderService.findReview(id));
    }

    @GetMapping("/product-review")
    public Message<List<ReviewGetResponse>> getProductReview(@RequestParam Long id) {
        return new Message<>(200, "물건 리뷰 조회", orderService.findProductReview(id));
    }



    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)

    public void createReview(@RequestBody ReviewCreateRequest createRequest, @RequestParam Long orderDetailId) {

        orderService.createReview(createRequest, orderDetailId);
    }


}
