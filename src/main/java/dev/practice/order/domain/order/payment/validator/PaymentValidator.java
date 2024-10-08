package dev.practice.order.domain.order.payment.validator;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;

public interface PaymentValidator {
    void validator(Order order, OrderCommand.PaymentRequest paymentRequest);
}
