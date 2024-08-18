package com.wefood.back.product.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductImageDetailResponse {
    private String img;
    private Boolean isThumbnail;
    private Integer sequence;

    @Builder
    public ProductImageDetailResponse(String img, Boolean isThumbnail, Integer sequence) {
        this.img = img;
        this.isThumbnail = isThumbnail;
        this.sequence = sequence;
    }

    public void setName(String img) {
        this.img = img;
    }
}