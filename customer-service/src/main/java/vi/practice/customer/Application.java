package vi.practice.customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import vi.practice.customer.contract.services.protobuf.customer.model.*;
import vi.practice.customer.data.*;


@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

    @Bean
    CustomerRepository repository() {
        List<CustomerProto.Customer> customers = new ArrayList<>();
        customers.add(CustomerProto.Customer.newBuilder().setId(1).setAadhar("12345").setName("Adam Kowalski")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(2).setAadhar("12346").setName("Anna Malinowska")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(3).setAadhar("12347").setName("Paweł Michalski")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(4).setAadhar("12348").setName("Karolina Lewandowska")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        return new CustomerRepository(customers);
    }

}
