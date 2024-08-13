package com.wefood.back.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailResponseDto {

    private Long id;
    private String name;
    private String detail;
    private int price;

    @Builder
    public ProductDetailResponseDto(Long id, String name, String detail, int price) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
    }
}
