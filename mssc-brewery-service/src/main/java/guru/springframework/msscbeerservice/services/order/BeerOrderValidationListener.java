package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final BeerValidator beerValidator;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void validateOrder(ValidateOrderRequest validateOrderRequest){

        Boolean isValid = beerValidator.validateOrder(validateOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult
            .builder()
            .isValid(isValid)
            .orderId(validateOrderRequest.getBeerOrderDto().getId())
            .build());
    }
}
