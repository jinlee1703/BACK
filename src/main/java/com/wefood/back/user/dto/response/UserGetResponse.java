package com.wefood.back.user.dto.response;

public record UserGetResponse(Long id,String phoneNumber, String password, String name, Boolean isSeller, Boolean isResign,
                              String accountNumber) {
}
