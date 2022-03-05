package guru.sfg.beer.order.service.controllers;

import guru.sfg.beer.order.service.bootstrap.BeerOrderBootStrap;
import guru.sfg.beer.order.service.domain.BeerOrder;
import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.domain.BeerOrderManager;
import guru.sfg.beer.order.service.domain.Customer;
import guru.sfg.beer.order.service.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

// This service fakes an order of a client

@Service
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerRepository customerRepository;
    private final BeerOrderManager beerOrderManager;

    //@Scheduled(fixedDelay = 100000)
    private void generateOrder() {

        String beerToOrder = BeerOrderBootStrap.BEER_1_UPC;

        BeerOrderLine beerOrderLine = BeerOrderLine.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();

        Set<BeerOrderLine> beerOrderLineSet = new HashSet<BeerOrderLine>();
        beerOrderLineSet.add(beerOrderLine);

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderBootStrap.TASTING_ROOM);
        Customer customer = customerList.get(0);


        BeerOrder beerOrder = BeerOrder.builder()
                .customer(customer)
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineSet)
                .build();

        beerOrderManager.newBeerOrder(beerOrder);

    }
}
