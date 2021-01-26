package br.com.andre.msscbeerservice.config;

import br.com.andre.msscbeerservice.web.model.events.BrewBeerEvent;
import br.com.andre.msscbeerservice.web.model.events.NewInventoryEvent;
import br.com.andre.msscbeerservice.web.model.events.ValidateOrderResult;
import br.com.andre.msscbeerservice.web.model.events.ValidateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    public static final String BREWING_REQUEST_QUEUE = "brewing-request";
    public static final String NEW_INVENTORY_QUEUE = "new-inventory";
    public static final String VALIDATE_ORDER_QUEUE = "validate-order";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";

    @Bean
    public MessageConverter jacksonJmsConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("JMS_TYPE", NewInventoryEvent.class);
        typeIdMappings.put("JMS_VALIDATE_REQUEST", ValidateOrderRequest.class);
        typeIdMappings.put("JMS_VALIDATE_RESPONSE", ValidateOrderResult.class);
        typeIdMappings.put("JMS_BREWING_REQUEST", BrewBeerEvent.class);
        converter.setTypeIdPropertyName("_type");
        converter.setTypeIdMappings(typeIdMappings);
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
