package com.wefood.back.farm.dto;

import com.wefood.back.global.image.dto.ImageDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmResponse {

    private Long id;
    private String name;
    private List<ImageDetailResponse> img;
}
