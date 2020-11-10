package br.com.andre.msscbeerservice.services.listeners;

import br.com.andre.msscbeerservice.repositories.BeerRepository;
import br.com.andre.msscbeerservice.web.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder) {
        AtomicInteger beersNotFount = new AtomicInteger();

        beerOrder.getBeerOrderLines().forEach(orderLine -> {
            if (beerRepository.findByUpc(orderLine.getUpc()) == null) {
                beersNotFount.incrementAndGet();
            }
        });

        return beersNotFount.get() == 0;
    }

}
