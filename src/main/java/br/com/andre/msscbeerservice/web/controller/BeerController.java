package br.com.andre.msscbeerservice.web.controller;

import br.com.andre.msscbeerservice.services.BeerService;
import br.com.andre.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return ResponseEntity.ok(beerService.getbyId(beerId));
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) throws MalformedURLException, URISyntaxException {
        BeerDto saved = beerService.saveNewBeer(beerDto);
        return ResponseEntity.created(new UriTemplate("/api/v1/beer/{beerId}")
                .expand(saved.getId().toString())
                .toURL().toURI()).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

}
