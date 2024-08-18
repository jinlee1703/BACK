package com.wefood.back.order.service;

import com.wefood.back.order.dto.CartProductRequest;
import com.wefood.back.order.dto.CartProductResponse;
import com.wefood.back.order.entity.Cart;
import com.wefood.back.order.entity.Cart.Pk;
import com.wefood.back.order.repository.CartRepository;
import com.wefood.back.product.entity.Product;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.product.repository.ProductRepository;
import com.wefood.back.user.entity.User;
import com.wefood.back.user.exception.UserNotFoundException;
import com.wefood.back.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private static final String imgRoute = "https://s3.ap-northeast-2.amazonaws.com";
    private static final String productURL = "/product/";
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CartProductResponse> getCartByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        List<CartProductResponse> products = productRepository.findCartProductByUserId(userId);
        for (CartProductResponse product : products) {
            product.setThumbnail(imgRoute + "/" + bucketName + productURL + product.getId() + "/" + product.getThumbnail());
        }

        return products;
    }

    @Transactional
    public void saveCartProduct(CartProductRequest requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        Pk pk = Pk.builder().productId(requestDto.getProductId()).userId(requestDto.getUserId()).build();
        Cart cart = Cart.builder().product(product).user(user).quantity(requestDto.getQuantity()).pk(pk).build();

        if (cartRepository.existsById(pk)) {
            cartRepository.findById(pk).get().updateQuantity(requestDto.getQuantity());
        } else {
            cartRepository.save(cart);
        }
    }
}
