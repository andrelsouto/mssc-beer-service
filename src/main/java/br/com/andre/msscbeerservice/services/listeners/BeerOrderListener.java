package br.com.andre.msscbeerservice.services.listeners;

import br.com.andre.msscbeerservice.config.JmsConfig;
import br.com.andre.msscbeerservice.events.ValidateOrderResult;
import br.com.andre.msscbeerservice.web.events.ValidateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        log.debug("Validate request beer order id: " + validateOrderRequest.getBeerOrder().getId());

        Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrder());

        ValidateOrderResult orderResult = ValidateOrderResult.builder()
                .beerOrderId(validateOrderRequest.getBeerOrder().getId())
                .isValid(isValid)
                .build();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, orderResult);

    }

}
