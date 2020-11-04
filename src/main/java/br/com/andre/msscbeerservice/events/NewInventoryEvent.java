package br.com.andre.msscbeerservice.events;

import br.com.andre.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
