package com.wefood.back.farm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FarmImageResponse {
    private String img;
    private Boolean isThumbnail;
    private Integer sequence;

    @Builder
    public FarmImageResponse(String img, Boolean isThumbnail, Integer sequence) {
        this.img = img;
        this.isThumbnail = isThumbnail;
        this.sequence = sequence;
    }

    public void setName(String img) {
        this.img = img;
    }
}


