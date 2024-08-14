package com.wefood.back.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDetailResponse {

    private Long id;
    private String name;
    private String detail;
    private int price;
    private Long farmId;
    private String farmName;
    private List<ProductImageResponse> img;

    @Builder
    public ProductDetailResponse(Long id, String name, String detail, int price, Long farmId, String farmName) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.farmId = farmId;
        this.farmName = farmName;
    }

    public void setImg(List<ProductImageResponse> img) {
        this.img = img;
    }
}
