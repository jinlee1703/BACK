package com.wefood.back.product.dto;

import com.wefood.back.global.image.dto.ImageDetailResponse;
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
    private Long itemId;
    private List<ImageDetailResponse> productImg;

    private Long farmId;
    private String farmName;
    private String farmDetail;
    private List<ImageDetailResponse> farmImg;

    @Builder
    public ProductDetailResponse(Long id, String name, String detail, int price, Long itemId) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.itemId = itemId;
    }

    public void setProductImg(List<ImageDetailResponse> productImg) {
        this.productImg = productImg;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setFarmImg(List<ImageDetailResponse> farmImg) {
        this.farmImg = farmImg;
    }

    public void setFarmDetail(String farmDetail) {
        this.farmDetail = farmDetail;
    }
}
