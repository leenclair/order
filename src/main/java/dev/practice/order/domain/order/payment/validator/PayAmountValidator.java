package dev.practice.order.domain.order.payment.validator;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator{
    @Override
    public void validator(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if(!order.calculateTotalAmount().equals(paymentRequest.getAmount())) throw new InvalidParamException("주문 가격이 불일치합니다.");
    }
}
