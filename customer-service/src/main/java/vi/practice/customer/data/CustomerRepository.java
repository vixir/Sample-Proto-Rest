package vi.practice.customer.data;


import protobuf.customer.model.*;

import java.util.*;

public class CustomerRepository {

    private List<CustomerProto.Customer> customers;

    public CustomerRepository(List<CustomerProto.Customer> customers) {
        this.customers = customers;
    }

    public CustomerProto.Customer findById(int id) {
        return customers.stream().filter(it -> it.getId() ==id).findFirst().get();
    }

    public CustomerProto.Customer findByAadhar(String aadhar) {
        return customers.stream().filter(it -> it.getAadhar().equals(aadhar)).findFirst().get();
    }

    public List<CustomerProto.Customer> findAll() {
        return customers;
    }

}