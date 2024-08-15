package com.wefood.back.product.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductImageDetailResponse {
    private String name;
    private Boolean isThumbnail;

    @Builder
    public ProductImageDetailResponse(String name, Boolean isThumbnail) {
        this.name = name;
        this.isThumbnail = isThumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }
}