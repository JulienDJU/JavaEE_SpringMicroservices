package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);

    CustomerDto save(CustomerDto customer);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void delete(UUID id);
}
