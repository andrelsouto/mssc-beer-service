package br.com.andre.msscbeerservice.web.mappers;

import br.com.andre.msscbeerservice.domain.Beer;
import br.com.andre.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

}
