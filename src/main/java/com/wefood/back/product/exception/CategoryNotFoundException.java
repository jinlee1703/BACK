package com.wefood.back.product.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("카테고리 조회 실패");
    }
}
