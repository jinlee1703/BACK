package com.wefood.back.order.controller;

import com.wefood.back.global.Message;
import com.wefood.back.order.dto.CartProductResponse;
import com.wefood.back.order.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Message<List<CartProductResponse>>> getCart(@RequestParam("userId") Long userId) {
        List<CartProductResponse> cart = cartService.getCartByUserId(userId);
        Message<List<CartProductResponse>> message = new Message<>(200, "장바구니 조회 성공", cart);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
