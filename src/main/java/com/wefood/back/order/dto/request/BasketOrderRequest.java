package com.wefood.back.order.dto.request;

import java.util.List;

public record BasketOrderRequest(OrderCreateRequest orderCreateRequest, List<CartRequest> farms) {
}
