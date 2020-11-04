package br.com.andre.msscbeerservice.events;

import br.com.andre.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
