package br.com.andre.msscbeerservice.services;

import br.com.andre.msscbeerservice.domain.Beer;
import br.com.andre.msscbeerservice.repositories.BeerRepository;
import br.com.andre.msscbeerservice.specifications.BeerSpecification;
import br.com.andre.msscbeerservice.web.controller.NotFoundException;
import br.com.andre.msscbeerservice.web.mappers.BeerMapper;
import br.com.andre.msscbeerservice.web.model.BeerDto;
import br.com.andre.msscbeerservice.web.model.BeerPagedList;
import br.com.andre.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return  beerMapper.beerToBeerDto(beerRepository.findById(beerId)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
        Specification<Beer> specification = Specification.where(beerName == null ? null : BeerSpecification.filterName(beerName))
                .and(beerStyle == null ? null : BeerSpecification.filterBeerStyle(beerStyle));
        Page<Beer> beerPage = beerRepository.findAll(specification, pageRequest);

        BeerPagedList beerPagedList = new BeerPagedList(
                beerPage.getContent()
                .stream().map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest.of(
                        beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements()
        );

        return beerPagedList;
    }
}
