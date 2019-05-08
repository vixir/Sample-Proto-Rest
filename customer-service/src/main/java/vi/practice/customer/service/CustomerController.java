package vi.practice.customer.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import vi.practice.customer.contract.*;
import vi.practice.customer.contract.services.protobuf.customer.model.*;
import vi.practice.customer.data.*;

import java.util.logging.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    HomeClient homeClient;

    protected Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    @RequestMapping(value = "/customers/aadhar/{aadhar}", produces = "application/x-protobuf")
    public CustomerProto.Customer findByAadhar(@PathVariable("aadhar") String aadhar) {
        LOGGER.info(String.format("Customer.findByAadhar(%s)", aadhar));
        return repository.findByAadhar(aadhar);
    }

    @RequestMapping(value = "/customers", produces = "application/x-protobuf")
    public CustomerProto.Customers findAll() {
        LOGGER.info("Customer.findAll()");
        return CustomerProto.Customers.newBuilder().addAllCustomers(repository.findAll()).build();
    }

    @RequestMapping(value = "/customers/{id}", produces = "application/x-protobuf")
    public CustomerProto.Customer findById(@PathVariable("id") Integer id) {
        LOGGER.info(String.format("Customer.findById(%s)", id));
        CustomerProto.Customer customer = repository.findById(id);
        CustomerProto.Homes homes =  homeClient.getHomes(id);
        customer = CustomerProto.Customer.newBuilder(customer).addAllHomes(homes.getHomeList()).build();
        return customer;
    }

}