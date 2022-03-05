package guru.sfg.brewery.controller;

import guru.sfg.brewery.model.BeerInventoryDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class InventoryFailoverController {

    @GetMapping("/api/v1/inventory-failover")
    public Mono<BeerInventoryDto> handleGetRequest() {
        return Mono.just(new BeerInventoryDto());
    }
}
