package com.wefood.back.order.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderCreateRequest(Integer totalCost, String invoiceNumber, String receiverPhoneNumber,
                                 String receiverName, String receiverAddress, String receiverAddressDetail,
                                 LocalDate deliveryDate, LocalDateTime meetingAt) {
}
