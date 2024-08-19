package com.wefood.back.order.controller;


import com.wefood.back.global.Message;
import com.wefood.back.order.dto.request.BasketOrderRequest;
import com.wefood.back.order.dto.request.DirectOrderCreateRequest;
import com.wefood.back.order.dto.response.OrderDetailGetResponse;
import com.wefood.back.order.dto.response.OrderGetResponse;
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


    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@PathVariable Long userId, @RequestBody DirectOrderCreateRequest orderCreateRequest) {
        orderService.createOrder(userId,orderCreateRequest);
    }

    @PostMapping("/{userId}/basket")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBasketOrder(@PathVariable Long userId, @RequestBody BasketOrderRequest basketOrderRequest) {
        orderService.createBasketOrder(userId,basketOrderRequest);
    }

    @GetMapping("/{id}")
    public Message<List<OrderDetailGetResponse>> getOrder(@PathVariable Long id) {
        return new Message<>(200,"주문조회",orderService.findOrder(id));
    }

    @GetMapping
    public Message<List<OrderGetResponse>> getAllOrders(@RequestParam Long userId) {
        return new Message<>(200, "주문리스트 조회", orderService.findOrderList(userId));
    }

}
