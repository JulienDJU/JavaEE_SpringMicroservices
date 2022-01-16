package guru.sfg.beer.order.service.controllers;

import guru.sfg.beer.order.service.bootstrap.BeerOrderBootStrap;
import guru.sfg.beer.order.service.domain.*;
import guru.sfg.beer.order.service.repositories.BeerOrderRepository;
import guru.sfg.beer.order.service.repositories.CustomerRepository;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.util.*;

// This service fakes an order of a client

@Service
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerRepository customerRepository;
    private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachineFactory;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderManager beerOrderManager;

    @Scheduled(fixedDelay = 100000)
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
