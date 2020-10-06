package br.com.andre.msscbeerservice.web.controller;

import br.com.andre.msscbeerservice.services.BeerService;
import br.com.andre.msscbeerservice.web.model.BeerDto;
import br.com.andre.msscbeerservice.web.model.BeerPagedList;
import br.com.andre.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(required = false, defaultValue = "25") Integer pageSize,
                                                   @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand,
                                                   @RequestParam(required = false) String beerName,
                                                   @RequestParam(required = false) BeerStyleEnum beerStyle) {

        return ResponseEntity.ok(beerService.listBeers(beerName, beerStyle, showInventoryOnHand, PageRequest.of(pageNumber, pageSize)));

    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return ResponseEntity.ok(beerService.getById(beerId, showInventoryOnHand));
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) throws MalformedURLException, URISyntaxException {
        BeerDto saved = beerService.saveNewBeer(beerDto);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

}
