package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {

        AtomicBoolean isValid = new AtomicBoolean(true);
        beerOrderDto.getBeerOrderLines()
                .stream()
                .forEach(beerOrderLineDto -> {
                    String upc = beerOrderLineDto.getUpc();
                    Beer beer = beerRepository.findByUpc(upc);
                    if (beer == null) {
                        isValid.set(false);
                    }

                });

        return true;
    }
}
