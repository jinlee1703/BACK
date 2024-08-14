package com.wefood.back.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {
    private String name;
    private Boolean isThumbnail;

    public void setName(String name) {
        this.name = name;
    }
}