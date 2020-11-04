package br.com.andre.msscbeerservice.events;

import br.com.andre.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 5769432483433890163L;

    private BeerDto beerDto;

}
