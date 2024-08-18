package com.wefood.back.order.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartProductRequest {

    @Min(value = 1)
    private Long userId;

    @Min(value = 1)
    private Long productId;

    @Min(value = 1)
    private Integer quantity;
}
