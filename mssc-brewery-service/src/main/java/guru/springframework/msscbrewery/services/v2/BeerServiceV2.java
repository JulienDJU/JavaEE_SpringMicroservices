package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    Object getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDtoV2 beerDto);
}
