package com.wefood.back.farm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmListResponse {
    private Long id;
    private String name;
    private String img;

    public void setImg(String img) {
        this.img = img;
    }
}
