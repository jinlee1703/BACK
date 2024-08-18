package com.wefood.back.user.dto.response;

public record UserLoginResponse(String name, Long id, boolean isSeller) {
}
