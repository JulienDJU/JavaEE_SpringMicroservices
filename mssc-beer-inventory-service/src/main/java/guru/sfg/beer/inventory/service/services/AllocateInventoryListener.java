package guru.sfg.beer.inventory.service.services;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateInventoryListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void allocateInventoryListener(AllocateOrderRequest allocateOrderRequest){
        BeerOrderDto beerOrderDto = allocateOrderRequest.getBeerOrderDto();
        Boolean allocatedOrder = allocationService.allocateOrder(beerOrderDto);

        AllocateOrderResult.AllocateOrderResultBuilder builder= AllocateOrderResult.builder();
        builder.beerOrderDto(beerOrderDto);

        try {
            Boolean allocationResult= allocationService.allocateOrder(beerOrderDto);

            if (allocationResult) {
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }
            builder.allocationError(false);
        } catch (Exception e){
            log.error("Allocation failed for Order Id: " + allocateOrderRequest.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
