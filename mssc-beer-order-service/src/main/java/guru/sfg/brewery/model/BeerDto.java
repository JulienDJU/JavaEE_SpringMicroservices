package guru.sfg.brewery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

    private UUID id;
    private String beerName;
    private String upc;
    private String beerStyle;
    private BigDecimal price;

}
