package com.wefood.back.farm.exception;

public class FarmNotFoundException extends RuntimeException{

    public FarmNotFoundException() {
        super("농가 조회 실패");
    }
}
