package com.wefood.back.order.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderCreateRequest(String orderStatus, int totalCost, String invoiceNumber, String receiverPhoneNumber,
                                 String receiverName, LocalDate deliveryDate, LocalDateTime meetingAt) {
}
