package br.com.andre.msscbeerservice.services.inventory.model;

import br.com.andre.msscbeerservice.web.model.OffsetDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {

    private UUID id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonDeserialize(using = OffsetDateDeserializer.class)
    private OffsetDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonDeserialize(using = OffsetDateDeserializer.class)
    private OffsetDateTime lastModifiedDate;
    private UUID beerId;
    private String upc;
    private Integer quantityOnHand;

}
