package com.wefood.back.global.image.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageDetailResponse {
    private String img;
    private Boolean isThumbnail;
    private Integer sequence;

    @Builder
    public ImageDetailResponse(String img, Boolean isThumbnail, Integer sequence) {
        this.img = img;
        this.isThumbnail = isThumbnail;
        this.sequence = sequence;
    }

    public void setName(String img) {
        this.img = img;
    }
}