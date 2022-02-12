package guru.sfg.beer.order.service.services.listeners;

import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.beer.order.service.domain.BeerOrderManager;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllocationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void allocateOrderResponse(AllocateOrderResult result){
        BeerOrderDto beerOrderDto = result.getBeerOrderDto();

        if (result.getAllocationError()){
            beerOrderManager.beerOrderAllocationFailed(beerOrderDto);
        } else if (result.getPendingInventory() && !result.getAllocationError()) {
            beerOrderManager.beerOrderAllocationPendingInventory(beerOrderDto);
        } else if (!result.getPendingInventory() && !result.getAllocationError()){
            beerOrderManager.beerOrderAllocationPassed(beerOrderDto);
        }
    }
}
