package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.inventory")
@Service
public class BeerServiceImpl implements  BeerService{

    private final RestTemplate restTemplate;

    private String beerServiceHost;
    private String BEER_UPC_PATH = "/api/v1/beerUpc/";
    private String BEER_PATH = "/api/v1/beer/";

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        System.out.println(String.format("Beer service host is [%s]", beerServiceHost));

        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_UPC_PATH + upc, BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_PATH + id.toString(), BeerDto.class));
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }
}
