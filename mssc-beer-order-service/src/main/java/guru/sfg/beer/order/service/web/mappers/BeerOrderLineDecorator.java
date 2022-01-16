package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerService;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLineDecorator implements BeerOrderLineMapper{

    private BeerOrderLineMapper beerOrderLineMapper;
    private BeerService beerService;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setMapper(BeerOrderLineMapper mapper) {
        this.beerOrderLineMapper = mapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if (line == null) {
            return new BeerOrderLineDto();
        }
        BeerOrderLineDto beerOrderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);

        String upc = beerOrderLineDto.getUpc();

        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(upc);

        beerDtoOptional.ifPresent(beerDto -> {
            beerOrderLineDto.setBeerId(beerDto.getId());
            beerOrderLineDto.setBeerName(beerDto.getBeerName());
            beerOrderLineDto.setBeerStyle(beerDto.getBeerStyle());
            beerOrderLineDto.setPrice(beerDto.getPrice());
        });

        return beerOrderLineDto;
    }

}
