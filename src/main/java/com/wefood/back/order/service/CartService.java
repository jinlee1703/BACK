package com.wefood.back.order.service;

import com.wefood.back.order.dto.CartProductResponse;
import com.wefood.back.order.repository.CartRepository;
import com.wefood.back.product.repository.ProductRepository;
import com.wefood.back.user.exception.UserNotFoundException;
import com.wefood.back.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<CartProductResponse> getCartByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        return productRepository.findCartProductByUserId(userId);
    }
}
