package com.wefood.back.farm.dto;

public interface FarmInfoResponse {

    Farm getFarm();
    interface Farm {
        Long getId();
        String getName();
        String getDetail();
    }
}
