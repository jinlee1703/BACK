package com.wefood.back.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("상품 조회 실패");
    }
}
