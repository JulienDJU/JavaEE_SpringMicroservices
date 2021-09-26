package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Bil")
                .build();
    }

    @Override
    public CustomerDto save(CustomerDto customer) {
        customer.setId(UUID.randomUUID());
        return customer;
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        // 1 find customer

        // 2 for each non null field of customerDto, update found customer in step 1

        // 3 save update customer
        log.debug("Updating...");
    }

    @Override
    public void delete(UUID id) {
        log.debug("Deleting...");
    }
}
