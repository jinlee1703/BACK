package com.wefood.back.order.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderGetResponse(Long id,String orderStatusId, int totalCost, String invoiceNumber, String receiverPhoneNumber,
                               String receiverName, LocalDate deliveryDate, LocalDateTime meetingAt) {
}
