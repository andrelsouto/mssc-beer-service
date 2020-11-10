package br.com.andre.msscbeerservice.repositories;

import br.com.andre.msscbeerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID>, JpaSpecificationExecutor<Beer> {
    Optional<Beer> findByUpc(String upc);
    List<Beer> findAllByUpc(Iterable<String> upcs);
}
