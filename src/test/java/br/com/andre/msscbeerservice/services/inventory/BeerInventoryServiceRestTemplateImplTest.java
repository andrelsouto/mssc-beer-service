package br.com.andre.msscbeerservice.services.inventory;

import br.com.andre.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {

        Integer qoh = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);

        System.out.println(qoh);

    }
}