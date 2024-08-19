package com.wefood.back.order.dto.request;

import com.wefood.back.order.dto.CartProductResponse;

import java.util.List;

public record CartRequest(Long farmId,String farmName,List<CartProductResponse> products) {
}


