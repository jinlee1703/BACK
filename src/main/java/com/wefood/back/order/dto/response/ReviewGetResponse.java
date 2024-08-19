package com.wefood.back.order.dto.response;

public record ReviewGetResponse(Long id, Long orderDetailId, Long productId, String title, String content,
                                Integer score) {
}
