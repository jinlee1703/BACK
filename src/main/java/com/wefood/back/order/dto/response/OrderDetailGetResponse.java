package com.wefood.back.order.dto.response;

public record OrderDetailGetResponse(Long id, Long productId, Long reviewId, Integer quantity, Integer price) {
}
