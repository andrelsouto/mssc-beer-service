package br.com.andre.msscbeerservice.services.brewing;

import br.com.andre.msscbeerservice.config.JmsConfig;
import br.com.andre.msscbeerservice.domain.Beer;
import br.com.andre.msscbeerservice.events.BrewBeerEvent;
import br.com.andre.msscbeerservice.events.NewInventoryEvent;
import br.com.andre.msscbeerservice.repositories.BeerRepository;
import br.com.andre.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    private void listen(BrewBeerEvent event) {

        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}
