package br.com.andre.msscbeerservice.specifications;

import br.com.andre.msscbeerservice.domain.Beer;
import br.com.andre.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.jpa.domain.Specification;

public class BeerSpecification {

    public static Specification<Beer> filterName(String beerName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get("beerName")),
                "%" + beerName.toUpperCase() + "%");
    }

    public static Specification<Beer> filterBeerStyle(BeerStyleEnum beerStyle) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("beerStyle"), beerStyle);
    }

}
